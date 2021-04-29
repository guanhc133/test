package org.example.state.abstracts;

import org.example.state.enums.Status;
import org.example.state.service.ActivityService;

public class EditingState extends State {

    @Override
    public String arraignment(String activityId, Enum<Status>
            currentStatus) {
        ActivityService.execStatus(activityId, currentStatus,
                Status.Check);
        return "活动提审成功";
    }

    @Override
    public String checkPass(String activityId, Enum<Status> currentStatus) {
        return "编辑中不可审核通过";
    }

    @Override
    public String checkRefuse(String activityId, Enum<Status>
            currentStatus) {
        return "编辑中不可审核拒绝";
    }

    @Override
    public String checkRevoke(String activityId, Enum<Status>
            currentStatus) {
        return "编辑中不可撤销审核";
    }

    @Override
    public String close(String activityId, Enum<Status> currentStatus) {
        ActivityService.execStatus(activityId, currentStatus,
                Status.Close);
        return "活动关闭成功";
    }

    @Override
    public String open(String activityId, Enum<Status> currentStatus) {
        return "⾮关闭活动不可开启";
    }

    @Override
    public String doing(String activityId, Enum<Status> currentStatus) {
        return "编辑中活动不可执⾏活动中变更";
    }

}
