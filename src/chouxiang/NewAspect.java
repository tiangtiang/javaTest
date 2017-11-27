package chouxiang;

import chouxiang.aop.Before;
import chouxiang.aop.After;
import chouxiang.aop.Aspect;

/**
 * Created by lianglab on 2017/11/27.
 *
 * test outside package aspect
 */
@Aspect("chouxiang.aop.*")
public class NewAspect {
    @Before
    public void newBefore(){
        System.out.println("I'm the new before");
    }
    @After
    public void newAfter(){
        System.out.println("I'm the new after");
    }
}
