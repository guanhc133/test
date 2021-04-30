package org.example.design.state.abstracts;

import org.example.design.state.enums.Status;
import org.example.design.state.service.ActivityService;

public class PassState extends State {
    @Override
    public String arraignment(String activityId, Enum<Status> currentStatus) {
        return "审核成功不可提交审核中";
    }

    @Override
    public String checkPass(String activityId, Enum<Status> currentStatus) {
        ActivityService.execStatus(activityId, currentStatus,
                Status.Pass);
        return "活动审核完成";
    }

    @Override
    public String checkRefuse(String activityId, Enum<Status> currentStatus) {
        ActivityService.execStatus(activityId, currentStatus,
                Status.Refuse);
        return "活动审核拒绝";
    }

    @Override
    public String checkRevoke(String activityId, Enum<Status> currentStatus) {
        return "审核成功不可撤审";
    }

    @Override
    public String close(String activityId, Enum<Status> currentStatus) {
        return "审核成功不可活动关闭";
    }

    @Override
    public String open(String activityId, Enum<Status> currentStatus) {
        return "审核成功不可提交审核中";
    }

    @Override
    public String doing(String activityId, Enum<Status> currentStatus) {
        ActivityService.execStatus(activityId, currentStatus,
                Status.Doing);
        return "审核成功后活动进行中";
    }
}
