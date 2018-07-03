package leetcode;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by tiang on 2017/10/30.dd
 * 主函数
 */
public class TestMain {
    public static void main(String[] args) throws InterruptedException {
        TestThread ts = new TestThread();
        ts.start();
        ts.join();
        Thread.sleep(1000);
        LockSupport.unpark(ts);
        System.out.println(ts.value);
    }
}

class TestThread extends Thread{
    public int value = 100;
    @Override
    public void run() {
        value += 20;
        LockSupport.park();
        value = 100;
    }
}