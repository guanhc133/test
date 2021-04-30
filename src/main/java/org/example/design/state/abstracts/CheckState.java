package org.example.design.state.abstracts;

import org.example.design.state.enums.Status;
import org.example.design.state.service.ActivityService;

public class CheckState extends State {
    @Override
    public String arraignment(String activityId, Enum<Status> currentStatus) {
        return "待审核状态不可᯿复提审";
    }

    @Override
    public String checkPass(String activityId, Enum<Status> currentStatus) {
        ActivityService.execStatus(activityId, currentStatus,
                Status.Pass);
        return "活动审核通过完成";
    }

    @Override
    public String checkRefuse(String activityId, Enum<Status>
            currentStatus) {
        ActivityService.execStatus(activityId, currentStatus,
                Status.Refuse);
        return "活动审核拒绝完成";
    }

    @Override
    public String checkRevoke(String activityId, Enum<Status>
            currentStatus) {
        ActivityService.execStatus(activityId, currentStatus,
                Status.Editing);
        return "活动审核撤销回到编辑中";
    }

    @Override
    public String close(String activityId, Enum<Status> currentStatus) {
        ActivityService.execStatus(activityId, currentStatus,
                Status.Close);
        return "活动审核关闭完成";
    }

    @Override
    public String open(String activityId, Enum<Status> currentStatus) {
        return "⾮关闭活动不可开启";
    }

    @Override
    public String doing(String activityId, Enum<Status> currentStatus) {
        return "待审核活动不可执⾏活动中变更";
    }
}
