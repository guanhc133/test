package com.finance.test.msg.send.quartz;

import org.springframework.stereotype.Service;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: guanhc Date: 2017/5/12 ProjectName:test Version:
 */
@Service
public class MsgSendJob {
    /**
     * 定时任务执行（每秒）
     */
    public void start(){
        System.out.println("定时执行======");
    }
}
