import lombok.extern.slf4j.Slf4j;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/**
 *测试类的主方法，可根据需要自行进行选择策略
 * @author DAHUANG
 * @date 2022/8/9
 */
@Slf4j
public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(1, 1000, TimeUnit.MILLISECONDS, 1,((queue, task) -> {
            //queue.put(task);//1)死等
            //queue.offer(task,1500,TimeUnit.MILLISECONDS);//2)超时等待
            //log.debug("放弃执行{}",task);//3)放弃任务执行
            //throw new RuntimeException("任务执行失败"+task);//4)抛出异常
            //task.run();//5)让调用者自己执行任务
        }));
        for (int i = 0; i <4 ; i++) {
            int j=i;
            threadPool.execute(()->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("{}",j);
            });
        }
    }
