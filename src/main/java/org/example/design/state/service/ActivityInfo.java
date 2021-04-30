package org.example.design.state.service;

import lombok.Data;
import org.example.design.state.enums.Status;

import java.util.Date;

@Data
public class ActivityInfo {

    private String activityId; // 活动ID
    private String activityName; // 活动名称
    private Enum<Status> status; // 活动状态
    private Date beginTime; // 开始时间
    private Date endTime; // 结束时间
}
