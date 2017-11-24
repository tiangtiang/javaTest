package chouxiang.agent;

/**
 * Created by lianglab on 2017/11/24.
 */
public class TestAgent {
    public static void main(String[] args) {
        ProxyHandler proxyHandler = new ProxyHandler();
        Thing thing = (Thing)proxyHandler.bind(new Nothing());
        thing.doSomething();
    }
}
interface Thing{
    @Before(method = "beforeSome", parameterTypes = {"java.lang.String", "java.lang.Integer"},
            parameterValues = {"tiang!", "12"})
    @After(method = "afterSome")
    void doSomething();
}
interface Useless{
    void useThis();
}
class Nothing implements Thing, Useless{
    public void beforeSome(String name, Integer age){
        System.out.println("my name is "+name +" and my age is "+age);
    }
    @Override
    public void doSomething(){
        System.out.println("call doSomething by nothing");
    }
    public void afterSome(){
        System.out.println("after do something");
    }

    @Override
    public void useThis() {
        System.out.println("use this by nothing");
    }
}