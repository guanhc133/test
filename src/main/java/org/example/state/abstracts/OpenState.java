package org.example.state.abstracts;

import org.example.state.enums.Status;
import org.example.state.service.ActivityService;

public class OpenState extends State {

    @Override
    public String arraignment(String activityId, Enum<Status> currentStatus) {
        return "活动已开启，不可提交审核";
    }

    @Override
    public String checkPass(String activityId, Enum<Status> currentStatus) {
        return "活动已开启，不可审核通过";
    }

    @Override
    public String checkRefuse(String activityId, Enum<Status> currentStatus) {
        return "活动已开启，不可审核拒绝";
    }

    @Override
    public String checkRevoke(String activityId, Enum<Status> currentStatus) {
        return "活动已开启，不可撤回编辑中";
    }

    @Override
    public String close(String activityId, Enum<Status> currentStatus) {
        ActivityService.execStatus(activityId, currentStatus,
                Status.Close);
        return "活动已开启--活动关闭完成";
    }

    @Override
    public String open(String activityId, Enum<Status> currentStatus) {
        return "活动已开启";
    }

    @Override
    public String doing(String activityId, Enum<Status> currentStatus) {
        return null;
    }
}
