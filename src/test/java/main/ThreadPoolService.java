package main;

import java.util.concurrent.*;

/** 单例 线程池  后来好像没能用上
 * @author bufan
 * @data 2021/7/20
 */
public class ThreadPoolService {
//    private static final int DEFAULT_CORE_SIZE=5;
//    private static final int MAX_QUEUE_SIZE=5;
    private volatile static ThreadPoolExecutor executor;

    private ThreadPoolService() {};

    // 获取单例的线程池对象
    public static ThreadPoolExecutor getInstance(Integer size) {
        if (executor == null) {
            synchronized (ThreadPoolService.class) {
                if (executor == null) {
                    executor = new ThreadPoolExecutor(size,// 核心线程数
                            size, // 最大线程数
                            Integer.MAX_VALUE, // 闲置线程存活时间
                            TimeUnit.MILLISECONDS,// 时间单位
                            new LinkedBlockingDeque<Runnable>(Integer.MAX_VALUE),// 线程队列
                            Executors.defaultThreadFactory()// 线程工厂
                    );
                }
            }
        }
        return executor;
    }

    public void execute(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        executor.execute(runnable);
    }

    // 从线程队列中移除对象
    public void cancel(Runnable runnable) {
        if (executor != null) {
            executor.getQueue().remove(runnable);
        }
    }

}
