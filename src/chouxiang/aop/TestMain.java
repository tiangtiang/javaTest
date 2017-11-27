package chouxiang.aop;

/**
 * Created by lianglab on 2017/11/24.
 */
public class TestMain {
    public static void main(String[] args) {
        AgentExecute agent = new AgentExecute();
        Work work = (Work)agent.bind(new DayWork());
        work.sleep("tiang", 18);
    }
}

interface Work{
    @Point
    public boolean doWork(String name, int age);
    @Point
    public boolean sleep(String name, int age);
}

class DayWork implements Work{

    @Override
    public boolean doWork(String name, int age) {
        System.out.println(String.format("%s is working, and he is %d", name, age));
        if(age>20)
            return true;
        else
            return false;
    }

    @Override
    public boolean sleep(String name, int age) {
        System.out.println(String.format("%s has slept 8 hours", name));
        return true;
    }
}