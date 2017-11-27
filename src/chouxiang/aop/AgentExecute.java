package chouxiang.aop;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by lianglab on 2017/11/24.
 *
 * 代理执行类
 */
public class AgentExecute implements InvocationHandler {
    //被代理的对象
    private Object target;

    /**
     * 绑定对象
     * @param source
     * @return
     */
    public Object bind(Object source) {
        target = source;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                this);
    }

    /**
     * 模糊匹配(正则匹配）
     * @param patternStr
     * @param valueStr
     * @return
     */
    private boolean fuzzyMatching(String patternStr, String valueStr){
        try {
            Pattern pattern = Pattern.compile(patternStr);
            return pattern.matcher(valueStr).matches();
        }catch (PatternSyntaxException ex){
            return false;
        }
    }

    /**
     * 遍历整个包寻找切点处理类
     * @param packName 包名
     * @param methodName 方法的完整名
     * @return 类对象
     */
    private Class<?> getAspectClass(String packName, String methodName) {
        if (packName == null || packName.equals(""))
            return null;
        Set<Class<?>> classes = getClasses(packName);
        for (Class<?> cls : classes) {
            Aspect aspect = cls.getAnnotation(Aspect.class);
            if (aspect != null &&
                    //可以在这里进行模糊匹配
                    fuzzyMatching(aspect.value(), methodName)) {
                return cls;
            }
        }
        int lastDot = packName.lastIndexOf('.');
        if (lastDot == -1)
            return null;
        return getAspectClass(packName.substring(0, lastDot), methodName);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Point point = method.getAnnotation(Point.class);
        //并不是切点函数
        if (point == null) {
            return method.invoke(target, args);
        }
        //寻找切点对应的切面类
        String methodFullName = method.getDeclaringClass().getName() + "." + method.getName();
        Class<?> des = getAspectClass(target.getClass().getPackage().getName(), methodFullName);
        //找不到切面类
        if (des == null)
            return method.invoke(target, args);
        //获取切面类所有的函数
        Method[] methods = des.getMethods();
        //执行所有的Before函数，按声明顺序
        Arrays.stream(methods).filter(m->m.getAnnotation(Before.class)!=null).forEach(m->{
            try {
                executeMethod(des, m, m.getAnnotation(Before.class).value(), method, args, null);
            }catch (Exception ex){
                System.err.println("Execute before method: "+m.getName()+ " and a problem has occurred: "
                        + ex.getMessage());
            }
        });
        //执行目标函数，得到结果result
        try {
            Object result = method.invoke(target, args);
            //执行所有的After函数，按声明顺序
            Arrays.stream(methods).filter(m->m.getAnnotation(After.class)!=null).forEach(m->{
                try {
                    executeMethod(des, m, m.getAnnotation(After.class).value(), method, args, result);
                }catch (Exception ex){
                    System.err.println("Execute after method: "+m.getName()+ " and a problem has occurred: "
                            + ex.getMessage());
                }
            });
            return result;
        }catch (Throwable tr){
            //抛出异常，则执行异常处理函数
            Arrays.stream(methods).filter(m->m.getAnnotation(WhenThrow.class)!=null).forEach(m->{
                try {
                    executeMethod(des, m, m.getAnnotation(WhenThrow.class).value(), method, args, null);
                }catch (Exception ex){
                    System.err.println("Execute after method: "+m.getName()+ " and a problem has occurred: "
                            + ex.getMessage());
                }
            });
            throw tr;
        }finally {
            //执行所有的Finally函数，按声明顺序
            Arrays.stream(methods).filter(m->m.getAnnotation(Finally.class)!=null).forEach(m->{
                try {
                    executeMethod(des, m, m.getAnnotation(Finally.class).value(), method, args, null);
                }catch (Exception ex){
                    System.err.println("Execute after method: "+m.getName()+ " and a problem has occurred: "
                            + ex.getMessage());
                }
            });
        }
    }

    /**
     * 执行aop函数
     * @param des 切面类
     * @param method aop函数
     * @param para aop函数的参数列表，以逗号分隔
     * @param aim 切点函数
     * @param args  切点函数的参数列表
     * @param result 切点函数的返回值
     */
    private void executeMethod(Class<?> des, Method method, String para, Method aim, Object[] args, Object result)
            throws Exception {
        //如果没有aop函数没有参数，则直接执行
        if(para.equals("")){
            method.invoke(des.getConstructor().newInstance());
            return;
        }
        //有参数则解析参数
        String[] paraNames = para.split(",");
        Object[] paraObjs = new Object[paraNames.length];
        Parameter[] aimParas = aim.getParameters();
        for(int i=0;i<paraNames.length;i++){
            //如果参数名是result，则说明是目标函数的返回值。
            if(paraNames[i].equals("result")){
                paraObjs[i] = result;
                continue;
            }
            //从切点函数的参数列表中获取参数
            for(int j=0;j<aimParas.length;j++){
                //参数匹配的话，从切点函数的参数值列表中把值传给aop函数
                if(aimParas[j].getName().equals(paraNames[i])){
                    paraObjs[i] = args[j];
                    break;
                }
            }
            throw new Exception(String.format("Cant't find this parameter: %s", paraNames[i]));
        }
        //调用aop函数，并添加参数
        method.invoke(des.getConstructor().newInstance(), paraObjs);
    }

    /**
     * 从包package中获取所有的Class
     * 基本方法还是获取包编译后对应的class文件夹，从文件夹中获取所有的class文件名，继而获取所有的类
     *
     * @param pack 包名
     * @return 所有的类对象集合
     */
    private Set<Class<?>> getClasses(String pack) {

        // 第一个class类的集合
        Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
        // 是否循环迭代
        boolean recursive = true;
        // 获取包的名字 并进行替换
        String packageName = pack;
        String packageDirName = packageName.replace('.', '/');
        // 定义一个枚举的集合 并进行循环来处理这个目录下的things
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread()
                    .getContextClassLoader()
                    .getResources(packageDirName);
            // 循环迭代下去
            while (dirs.hasMoreElements()) {
                // 获取下一个元素
                URL url = dirs.nextElement();
                // 得到协议的名称
                String protocol = url.getProtocol();
                // 如果是以文件的形式保存在服务器上
                if ("file".equals(protocol)) {
                    // 获取包的物理路径
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    // 以文件的方式扫描整个包下的文件 并添加到集合中
                    findAndAddClassesInPackageByFile(packageName,
                            filePath, recursive, classes);
                } else if ("jar".equals(protocol)) {
                    // 如果是jar包文件
                    // 定义一个JarFile
                    System.err.println("jar类型的扫描");
                    JarFile jar;
                    try {
                        // 获取jar
                        jar = ((JarURLConnection) url.openConnection()).getJarFile();
                        // 从此jar包 得到一个枚举类
                        Enumeration<JarEntry> entries = jar.entries();
                        // 同样的进行循环迭代
                        while (entries.hasMoreElements()) {
                            // 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
                            JarEntry entry = entries.nextElement();
                            String name = entry.getName();
                            // 如果是以/开头的
                            if (name.charAt(0) == '/') {
                                // 获取后面的字符串
                                name = name.substring(1);
                            }
                            // 如果前半部分和定义的包名相同
                            if (name.startsWith(packageDirName)) {
                                int idx = name.lastIndexOf('/');
                                // 如果以"/"结尾 是一个包
                                if (idx != -1) {
                                    // 获取包名 把"/"替换成"."
                                    packageName = name.substring(0, idx).replace('/', '.');
                                }
                                // 如果可以迭代下去 并且是一个包
                                if ((idx != -1) || recursive) {
                                    // 如果是一个.class文件 而且不是目录
                                    if (name.endsWith(".class") && !entry.isDirectory()) {
                                        // 去掉后面的".class" 获取真正的类名
                                        String className = name.substring(
                                                packageName.length() + 1, name.length() - 6);
                                        try {
                                            // 添加到classes
                                            classes.add(Class.forName(packageName + '.' + className));
                                        } catch (ClassNotFoundException e) {
                                            // log
                                            // .error("添加用户自定义视图类错误 找不到此类的.class文件");
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                        }
                    } catch (IOException e) {
                        // log.error("在扫描用户定义视图时从jar包获取文件出错");
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return classes;
    }

    /**
     * 以文件的形式来获取包下的所有Class
     *
     * @param packageName
     * @param packagePath
     * @param recursive
     * @param classes
     */
    private void findAndAddClassesInPackageByFile(String packageName,
                                                 String packagePath, final boolean recursive, Set<Class<?>> classes) {
// 获取此包的目录 建立一个File
        File dir = new File(packagePath);
// 如果不存在或者 也不是目录就直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            // log.warn("用户定义包名 " + packageName + " 下没有任何文件");
            return;
        }
        //如果存在 就获取包下的所有文件 包括目录
        //自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
        File[] dirfiles = dir.listFiles(file -> (recursive && file.isDirectory()) || (file.getName().endsWith(".class")));
        //循环所有文件
        for (File file : dirfiles) {
            //如果是目录 则继续扫描
            if (file.isDirectory()) {
                findAndAddClassesInPackageByFile(packageName + "." + file.getName(),
                        file.getAbsolutePath(),
                        recursive,
                        classes);
            } else {
                //如果是java类文件 去掉后面的.class 只留下类名
                String className = file.getName().substring(0, file.getName().length() - 6);
                try {
                    //添加到集合中去
                    classes.add(Class.forName(packageName + '.' + className));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
