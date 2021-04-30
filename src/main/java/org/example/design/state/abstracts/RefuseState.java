package org.example.design.state.abstracts;

import org.example.design.state.enums.Status;
import org.example.design.state.service.ActivityService;

public class RefuseState extends State {
    @Override
    public String arraignment(String activityId, Enum<Status> currentStatus) {
        return "审核拒绝不可提交审核";
    }

    @Override
    public String checkPass(String activityId, Enum<Status> currentStatus) {
        return "审核拒绝不可审核通过";
    }

    @Override
    public String checkRefuse(String activityId, Enum<Status> currentStatus) {
        ActivityService.execStatus(activityId, currentStatus,
                Status.Refuse);
        return "活动审核拒绝完成";
    }

    @Override
    public String checkRevoke(String activityId, Enum<Status> currentStatus) {
        ActivityService.execStatus(activityId, currentStatus,
                Status.Editing);
        return "活动审核撤审成功";
    }

    @Override
    public String close(String activityId, Enum<Status> currentStatus) {
        ActivityService.execStatus(activityId, currentStatus,
                Status.Close);
        return "活动关闭成功";
    }

    @Override
    public String open(String activityId, Enum<Status> currentStatus) {
        return "活动审核拒绝不可开启活动";
    }

    @Override
    public String doing(String activityId, Enum<Status> currentStatus) {
        return "活动审核拒绝不可活动中";
    }
}
