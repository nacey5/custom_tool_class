import lombok.extern.slf4j.Slf4j;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程池
 * @author DAHUANG
 * @date 2022/8/9
 */
@Slf4j
class ThreadPool{
    //任务队列
    private BlockingQueue<Runnable> taskQueue;
    //线程集合
    private HashSet<Worker> workers=new HashSet<>();

    //核心线程类
    private int coreSize;

    //获取任务的超时时间
    private long timeout;

    //时间单位
    private TimeUnit timeUnit;

    private RejectPolicy<Runnable> rejectPolicy;

    /**
     * 执行任务
     * @param task
     */
    public void execute(Runnable task){
        //当任务数没有超过coreSize时，直接交给worker对象执行
        //如果任务数超过coreSize时，加入任务队列暂时缓存
        synchronized (workers){
            if (workers.size()<coreSize){
                Worker worker=new Worker(task);
                log.debug("新增 worker:{}",worker);
                workers.add(worker);
                worker.start();
            }else {
//                taskQueue.put(task);
                //1)死等
                //2)超时等待
                //3)放弃任务执行
                //4)抛出异常
                //5)让调用者自己执行任务

                taskQueue.tryPut(rejectPolicy,task);
            }
        }

    }



    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit,int queueCapacity,RejectPolicy<Runnable> rejectPolicy) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        taskQueue=new BlockingQueue<>(queueCapacity);
        this.rejectPolicy=rejectPolicy;
    }

    class Worker extends Thread{
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }


        @Override
        public void run() {
            //执行任务
            //当task不为空，执行任务
            //当task执行完毕，再接着从任务队列获取任务并执行
//            while (task!=null||(task=taskQueue.take())!=null){
            while (task!=null||(task=taskQueue.poll(timeout,timeUnit))!=null){
                try {
                    log.debug("正在执行...{}",task);
                    task.run();
                }catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    task=null;
                }
            }

            //当没有任务的时候，将执行此任务的worker移除掉
            synchronized (workers){
                log.debug("worker被移除{}",this);
                workers.remove(this);
            }

        }
    }
}
