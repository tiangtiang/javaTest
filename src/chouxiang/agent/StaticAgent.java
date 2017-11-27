package chouxiang.agent;

/**
 * Created by lianglab on 2017/11/27.
 *
 * 静态代理模式
 */
public class StaticAgent implements Something{
    private Shopping shopping;
    public StaticAgent(Shopping sp){
        shopping = sp;
    }
    @Override
    public void doSomething(){
        System.out.println("before I go shopping");
        shopping.doSomething();
        System.out.println("shoping is done");
    }

    public static void main(String[] args) {
        Something agent = new StaticAgent(new Shopping());
        agent.doSomething();
    }
}

interface Something{
    public void doSomething();
}

class Shopping implements Something{

    @Override
    public void doSomething() {
        System.out.println("I will go shop!");
    }
}