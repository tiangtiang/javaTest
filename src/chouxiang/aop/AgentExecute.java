package chouxiang.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by lianglab on 2017/11/24.
 *
 * 代理执行类
 */
public class AgentExecute implements InvocationHandler {
    private Object target;
    public  Object bind(Object source){
        target = source;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Point point = method.getAnnotation(Point.class);
        if(point == null) {
            return method.invoke(target, args);
        }else {
            Object result = method.invoke(target, args);
            return result;
        }
    }

    private Class getAspectClassInThePackage(String packageName){
        return null;
    }
}
