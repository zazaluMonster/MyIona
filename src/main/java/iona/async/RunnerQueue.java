package iona.async;

import iona.async.asyncRunner.AsyncRunner;
import iona.async.asyncRunner.BaseRunner;
import iona.exception.IonaException;
import iona.logger.IonaLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 一个简单的消息队列，可以接受asyncRunner，并按照先进先出的规则，一个个执行这些异步任务
 *
 * 如果你有些与主线程无关的业务逻辑，比如一些简单的log记录等，就可以交给本消息队列，慢慢处理
 *
 * 当队列满的时候处理方法：
 *
 * 1. 直接返回false，明确表示无法处理(本类目前处理方式)
 *
 * 2. 交给一个备用队列存储(不推荐)
 *
 * 3. 丢弃任务(不推荐)
 *
 * 4. 记录至数据库中备份，然后随后空闲时从数据库中获取记录进行处理(推荐记录至nosql中)
 */
@Component
public class RunnerQueue {

    //存储各类任务的队列
    private LinkedList<BaseRunner> queue = new LinkedList<>();

    //线程池,所有异步任务交给10个线程统一处理
    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    private int size = 0;

    //消息队列最大储量(包括正在执行的),若超过则不接受其他任务
    @Value("${iona.runner.queue.max.size}")
    private int MAX_SIZE;


    //由于存在"计数器"场景，本方法又是存在并发情况，所以添加synchronized控制
    public synchronized boolean addNewRunner(BaseRunner baseRunner){
        if(baseRunner == null){
            IonaLogger.info("Runner为空,无法添加任务");
            return false;
        }

        //队列已满,要求申请方稍后重试
        if(size >= MAX_SIZE){
            IonaLogger.info("消息队列已经满员，请稍后重试");
            return false;
        }

        queue.add(baseRunner);
        size++;

        //通知任务消费者
        consumeRunner();
        return true;
    }

    //执行任务，丢给线程池处理
    public void consumeRunner(){
        IonaLogger.info("消费任务进行中..");

        BaseRunner baseRunner = queue.poll();
        if(baseRunner != null){
            executorService.execute(baseRunner);
        }
    }

    //任务执行完毕的回调
    public synchronized void runnerComplete(){
        IonaLogger.info("某消息任务执行完毕");
        size--;
    }

}
