package multi;

import java.util.concurrent.*;

/**
 * Created by tiang on 2018/6/26.
 * future 模式
 */
public class FutureMain {

    private static class CountTask implements Callable<Integer>{
        private int a, b;
        public CountTask(int x, int y){
            a = x;
            b = y;
        }
        @Override
        public Integer call() throws Exception {
            System.out.println("开始执行");
            Thread.sleep(1000);
            System.out.println("执行完毕");
            return a+b;
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<Integer> task = new FutureTask<Integer>(new CountTask(2, 3));
        ExecutorService exe = Executors.newCachedThreadPool();
        exe.submit(task);
        System.out.println("请求完毕");
        while(!task.isDone()){
            Thread.sleep(100);
            System.out.println("wait done");
        }
        System.out.println("result = "+ task.get());
    }
}
