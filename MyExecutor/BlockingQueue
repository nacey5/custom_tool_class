import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import lombok.extern.slf4j.Slf4j;
/**
 *存放的任务阻塞队列
 * @author DAHUANG
 * @date 2022/8/9
 */
 
@Slf4j
class BlockingQueue<T>{
    //任务队列
    private Deque<T> queue=new ArrayDeque<>();
    //锁
    private ReentrantLock lock=new ReentrantLock();
    //生产者条件变量
    private Condition fullWaitSet=lock.newCondition();
    //消费者条件变量
    private Condition emptyWaitSet=lock.newCondition();
    //容量
    private int capcity;

    public BlockingQueue(int capcity) {
        this.capcity = capcity;
    }

    /**
     *带超时的阻塞获取
     * @return
     */
    public T poll(long timeout, TimeUnit timeUnit){
        lock.lock();
        try {
            //将timeout统一转换为 纳秒
            long nanos = timeUnit.toNanos(timeout);
            while (queue.isEmpty()){
                try {
                    //返回的是剩余时间
                    if (nanos<=0){
                        return null;
                    }
                    nanos = emptyWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signal();
            return t;
        }
        finally {
            lock.unlock();
        }
    }


    /**
     * 阻塞获取
     * @return
     */
    public T take(){
        lock.lock();
        try {
            while (queue.isEmpty()){
                try {
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signal();
            return t;
        }
        finally {
            lock.unlock();
        }
    }


    /**
     * 阻塞添加
     * @param task
     */
    public void put(T task){
        lock.lock();
        try {
            while (queue.size()==capcity){
                log.debug("等待加入任务队列{}...",task);
                fullWaitSet.await();
            }
            log.debug("加入任务队列{}",task);
            queue.addLast(task);
            emptyWaitSet.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 带超时时间的阻塞添加
     * @return
     */
    public boolean offer(T task,long timeout,TimeUnit timeUnit){
        lock.lock();
        try {
            long nanos = timeUnit.toNanos(timeout);
            while (queue.size()==capcity){
                log.debug("等待加入任务队列{}...",task);
                if (nanos<=0){
                    log.debug("添加失败--超时");
                    return false;
                }
                nanos = fullWaitSet.awaitNanos(nanos);
            }
            log.debug("加入任务队列{}",task);
            queue.addLast(task);
            emptyWaitSet.signal();
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return true;
    }


    /**
     * 获取大小
     * @return
     */
    public int size(){
        lock.lock();
        try {
            return queue.size();
        }finally {
            lock.unlock();
        }
    }

    public void tryPut(RejectPolicy<T> rejectPolicy, T task) {
        lock.lock();
        try {
            //判断队列是否为满的
            if (queue.size()==capcity){
                rejectPolicy.reject(this,task);
            }else {//有空闲
                log.debug("加入任务队列{}",task);
                queue.addLast(task);
                emptyWaitSet.signal();
            }
        }finally {
            lock.unlock();
        }
    }
}
