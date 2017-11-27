package chouxiang.aop;

/**
 * Created by lianglab on 2017/11/27.
 */
//@Aspect("chouxiang.aop.Work.*")
public class WorkAround {
    @Before
    public void startWork(){
        System.out.println("The working place is opening now!");
    }
    @Before("name,age")
    public void beforeDoWork(String name, int age){
        System.out.println(String.format("A new worker is ready for working! And his name is %s, %d years old!",
                name, age));
    }
    @After("result")
    public void afterDoWork(boolean result){
        if(result)
            System.out.println("He has done this work!");
        else
            System.out.println("He haven't finish this work!");
    }
}
