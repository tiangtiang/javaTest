package multi;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by tiang on 2018/6/25.
 */
public class RpcFramework {
    public static void export(final Object service, int port) throws Exception{
        if(service == null)
            throw new IllegalArgumentException("service instance is null");
        if(port<0 || port>65535)
            throw new IllegalArgumentException("invalid port");
        System.out.println("Export service "+service.getClass().getName()+" on port "+port);
        ServerSocket server = new ServerSocket(port);
        while(true){
            final Socket client = server.accept();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ObjectInputStream inputStream = null;
                    ObjectOutputStream outputStream = null;
                    try {
                        inputStream = new ObjectInputStream(client.getInputStream());
                        String methodName = inputStream.readUTF();
                        Class<?>[] parameterTypes = (Class<?>[]) inputStream.readObject();
                        Object[] parameters = (Object[]) inputStream.readObject();
                        Method method = service.getClass().getMethod(methodName, parameterTypes);
                        Object result = method.invoke(service, parameters);
                        outputStream = new ObjectOutputStream(client.getOutputStream());
                        outputStream.writeObject(result);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            if (inputStream != null)
                                inputStream.close();
                            if (outputStream != null)
                                outputStream.close();
                            client.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }

    public static <T> T refer(final Class<T> interfaces, final String host, final int port)throws Exception{
        if(interfaces == null)
            throw new IllegalArgumentException("interfaces class is null");
        if(!interfaces.isInterface())
            throw new IllegalArgumentException("invalid interface");
        if(host == null ||host.length() == 0)
            throw new IllegalArgumentException("invalid host");
        if(port<0 ||port> 65535)
            throw new IllegalArgumentException("invalid port");
        System.out.println("Get remote service "+interfaces.getName()+" from server "+host+":"+port);
        return (T) Proxy.newProxyInstance(interfaces.getClassLoader(), new Class[]{interfaces}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket client = new Socket(host, port);
                ObjectOutputStream outputStream = null;
                ObjectInputStream inputStream = null;
                try {
                    outputStream = new ObjectOutputStream(client.getOutputStream());
                    outputStream.writeUTF(method.getName());
                    outputStream.writeObject(method.getParameterTypes());
                    outputStream.writeObject(args);
                    inputStream = new ObjectInputStream(client.getInputStream());
                    Object result = inputStream.readObject();
                    return result;
                }finally {
                    if(outputStream!=null)
                        outputStream.close();
                    if(inputStream != null)
                        inputStream.close();
                    client.close();
                }
            }
        });
    }

    public static void main(String[] args) throws Exception {
        Hello h = new HelloWord();
        RpcFramework.export(h, 1023);
    }
}

class Person implements Serializable{
    private String name;
    public Person(String n){
        name = n;
    }
    public String getName(){
        return name;
    }
}

interface Hello{
    String sayHello(Person person);
}

class HelloWord implements Hello{

    @Override
    public String sayHello(Person person) {
        return "Hello, "+person.getName();
    }
}