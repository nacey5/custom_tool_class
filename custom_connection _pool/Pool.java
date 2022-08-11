import lombok.extern.slf4j.Slf4j;
import java.sql.*;
import java.util.Random;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 使用synchronized实现简单的单机连接
 * @author DAHUANG
 * @date 2022/8/8
 */

@Slf4j
class Pool{
    //连接池大小
    private  int poolSize;

    //连接数组对象
    private Connection[] connections;

    //连接状态数组 0表示空闲，1表示繁忙
    private AtomicIntegerArray states;

    //构造方法初始化属性

    public Pool(int poolSize) {
        this.poolSize = poolSize;
        this.connections = new Connection[poolSize];
        this.states = new AtomicIntegerArray(new int[poolSize]);
        for (int i = 0; i <poolSize ; i++) {
            connections[i]=new MockConnection("连接"+(i+1));
        }
    }

    //借出连接方法
    public Connection borrow(){
        while (true){
            //获取空闲连接
            for (int i = 0; i <poolSize ; i++) {
                if (states.get(i)==0) {
                    if (states.compareAndSet(i,0,1)) {
                        log.debug("borrow{}",connections[i]);
                        return connections[i];
                    }
                }
            }
            //如果没有空闲连接，当前线程进入等待
            synchronized (this){
                try {
                    log.debug("wait...");
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //归还连接方法
    public void free(Connection conn){
        for (int i = 0; i < poolSize; i++) {
            if (connections[i]==conn) {
                states.set(i,0);
                synchronized (this){
                    log.debug("free{}",conn);
                    this.notifyAll();
                }
                break;
            }
        }
    }
}
