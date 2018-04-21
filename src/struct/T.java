package struct;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by lianglab on 2018/4/13.
 */
public class T {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        /*Singleton singleton = Singleton.getInstance();
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("test"));
        out.writeObject(singleton);

        ObjectInputStream input = new ObjectInputStream(new FileInputStream("test"));
        Singleton s2 = (Singleton) input.readObject();
        System.out.println(singleton == s2);

        Class<Singleton> clazz = Singleton.class;
        Field f = clazz.getDeclaredField("flag");
        f.setAccessible(true);
        System.out.println(f.get(clazz));
        f.set(clazz, false);

        Constructor<Singleton> con = clazz.getDeclaredConstructor();
        con.setAccessible(true);
        Singleton s3 = con.newInstance();
        System.out.println(singleton == s3);*/
//        System.out.println(Load.name);
        EnumSingle single = EnumSingle.MONDAY;
        Class<EnumSingle> clazz = EnumSingle.class;
        Constructor<EnumSingle> cons = clazz.getDeclaredConstructor(String.class, int.class);
        cons.setAccessible(true);
        EnumSingle test = cons.newInstance("INS", 2);
        System.out.println(single == test);
    }
}

class Load{
    public static final String name;
    static {
        name = "hello";
        System.out.println("I am loading");
    }
}

class Singleton implements Serializable{
    private static volatile Singleton sin;
    private static volatile boolean flag = false;
    private Singleton() throws Exception {
        if(flag)
            throw new Exception("already exist");
        else
            flag = true;
    }
    public static Singleton getInstance() {
        if (sin == null)
            synchronized (Singleton.class) {
                if (sin == null)
                    try {
                        sin = new Singleton();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        return sin;
    }
    //反序列时直接返回当前INSTANCE
    private Object readResolve() {
        return sin;
    }
}

enum EnumSingle{
    MONDAY, TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY
}