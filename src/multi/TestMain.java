package multi;

/**
 * Created by tiang on 2018/6/24.
 */
public class TestMain {
    public static void main(String[] args) throws Exception {
        Hello h = RpcFramework.refer(Hello.class, "127.0.0.1", 1023);
        System.out.println(h.sayHello(new Person("tiang")));
    }
}
