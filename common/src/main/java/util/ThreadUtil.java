package util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/26 12:38
 * @Description:
 */
public class ThreadUtil {
    public static final int DEFAULT_THREAD_NUM = 8;
    private ExecutorService executorService;

    private ThreadUtil(int threadNum) {
        executorService = Executors.newFixedThreadPool(threadNum);
    }

    public static ThreadUtil init(int threadNum) {
        return new ThreadUtil(threadNum);
    }

    public static ThreadUtil init() {
        return new ThreadUtil(DEFAULT_THREAD_NUM);
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public void run(Runnable runnable) {
        executorService.execute(runnable);
    }

}
