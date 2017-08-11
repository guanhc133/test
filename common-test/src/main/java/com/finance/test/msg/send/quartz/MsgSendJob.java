package com.finance.test.msg.send.quartz;

import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: guanhc Date: 2017/5/12 ProjectName:test Version:
 */
@Service
public class MsgSendJob {
    private String filepath;

    /**
     * 定时任务执行（每秒）
     * @PostConstruct(项目启动时调用一次，此注解仅用于void方法上)
     */
//    @PostConstruct
    public void start() throws Exception{
        System.out.println("定时执行======"+filepath);
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFilepath() {
        return filepath;
    }
}
