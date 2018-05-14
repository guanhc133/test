package com.finance.test.msg.send.util.util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.*;

/**
 * <b>DESCRIPTION:</b>线程池工具类,可以根据自己的业务生成自己的线程池
 * 具体配置参考properties/threadpool.properties配置文件<br/>
 * <p>
 * <b>Create on:</b>2015年8月21日 15:04:22 <br/>
 *
 * @author: guanhc
 */
@Slf4j
public class ThreadPoolUtil {
    private static final String THREAD_POOL_FILE = "threadpool.properties";
    private static final String DEFAULT_PREFIX = "default";
    private static Map<String, ExecutorService> threadMap = new ConcurrentHashMap<String, ExecutorService>();
    private static final RejectedExecutionHandler HANDLER = new ThreadPoolExecutor.CallerRunsPolicy();

    public static ExecutorService createThreadPool(String preFix) throws Exception {
        preFix = preFix == null ? DEFAULT_PREFIX : preFix;
        Properties config = new Properties();
        config.load(ThreadPoolUtil.class.getClassLoader().getResourceAsStream(THREAD_POOL_FILE));
        ThreadModel threadModel = new ThreadModel();
        BeanInfo beanInfo = Introspector.getBeanInfo(threadModel.getClass());
        PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
        log.debug("【{}】",config.stringPropertyNames());
        for (String key : config.stringPropertyNames()) {
            if (key.startsWith(preFix)) {
                String fieldName = key.substring(preFix.length() + 1);
                for (PropertyDescriptor property : properties) {
                    if (property.getName().equals(fieldName)) {
                        property.getWriteMethod().invoke(threadModel, Integer.parseInt(config.getProperty(key)));
                    }
                }
            }
        }
        log.info("创建线程池,线程池名称前缀：[{}],属性：[{}]", preFix, threadModel);
        return createExecutorService(threadModel);
    }

    public static ExecutorService getExecuteThreadTask(String preFix) {
        preFix = preFix == null ? DEFAULT_PREFIX : preFix;
        return threadMap.get(preFix);
    }

    public static ExecutorService executeThreadTask(Runnable runnable) throws Exception {
        ExecutorService executorService = threadMap.get(DEFAULT_PREFIX);
        if (executorService == null) {
            executorService = createThreadPool(null);
            threadMap.put(DEFAULT_PREFIX, executorService);
        }
        executorService.execute(runnable);
        return executorService;
    }

    public static ExecutorService executeThreadTask(Runnable runnable, String prefix) throws Exception {
        ExecutorService executorService = threadMap.get(prefix);
        if (executorService == null) {
            executorService = createThreadPool(prefix);
            threadMap.put(prefix, executorService);
        }
        executorService.execute(runnable);
        return executorService;
    }

    private static ExecutorService createExecutorService(ThreadModel threadModel) {
        ExecutorService executorService = new ThreadPoolExecutor(threadModel.getMinSize(),
                threadModel.getMaxSize(), threadModel.getKeepAliveTime(), TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(threadModel.getWorkQueueSize()),
                HANDLER);
        return executorService;
    }


    @Data
    public static class ThreadModel {
        /**
         * 最小线程数
         */
        private int minSize = 1;
        /**
         * 最大线程
         */
        private int maxSize = 5;
        /**
         * 队列大小
         */
        private int queueSize = 3000;
        /**
         * 保持空闲的时间
         */
        private int keepAliveTime = 2000;

        /**
         * 线程池所使用的缓冲队列
         */
        private int workQueueSize = 2000;

    }

    private ThreadPoolUtil() {
    }
}
