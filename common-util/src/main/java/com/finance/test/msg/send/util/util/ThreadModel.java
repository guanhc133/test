package com.finance.test.msg.send.util.util;

import lombok.Data;

/**
 * Created by H7 on 2015/8/26.
 */
@Data
public class ThreadModel {
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
     *  线程池所使用的缓冲队列
     */
    private int workQueueSize = 2000;


}
