package chouxiang.agent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by lianglab on 2017/11/24.
 */
public class ProxyHandler implements InvocationHandler {
    private Object target = null;
    public Object bind(Object target){
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Before before = method.getAnnotation(Before.class);
        if(before!= null){
            executeMethod(target, before.method(), before.parameterTypes(), before.parameterValues());
        }
        Object result = method.invoke(target, args);
        After after = method.getAnnotation(After.class);
        if(after != null){
            executeMethod(target, after.method(), after.parameterTypes(), after.parameterValues());
        }
        return result;
    }

    /**
     * 执行函数
     * @param source 函数的调用对象
     * @param methodName 函数名
     * @param typeStr 参数类型列表
     * @param pvalues 参数值（全部为String）
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void executeMethod(Object source, String methodName, String[] typeStr, String[] pvalues) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (!(typeStr.length == 1 && typeStr[0].equals(""))) {
            Class[] types = new Class[typeStr.length];
            Object[] values = new Object[types.length];
            for (int i = 0; i < typeStr.length; i++) {
                types[i] = Class.forName(typeStr[i]);
                if(types[i] == Integer.class){
                    values[i] = Integer.parseInt(pvalues[i]);
                }else if(types[i] == Double.class){
                    values[i] = Double.parseDouble(pvalues[i]);
                }
            }
            Method method = source.getClass().getMethod(methodName, types);
            if (method != null)
                method.invoke(source, values);
        } else {
            Method method = source.getClass().getMethod(methodName);
            if (method != null)
                method.invoke(source);
        }
    }
}
