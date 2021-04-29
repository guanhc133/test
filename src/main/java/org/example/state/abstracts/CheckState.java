package org.example.state.abstracts;

import org.example.state.enums.Status;

public class CheckState extends State {
    @Override
    public String arraignment(String activityId, Enum<Status> currentStatus) {
        return null;
    }

    @Override
    public String checkPass(String activityId, Enum<Status> currentStatus) {
        return null;
    }

    @Override
    public String checkRefuse(String activityId, Enum<Status> currentStatus) {
        return null;
    }

    @Override
    public String checkRevoke(String activityId, Enum<Status> currentStatus) {
        return null;
    }

    @Override
    public String close(String activityId, Enum<Status> currentStatus) {
        return null;
    }

    @Override
    public String open(String activityId, Enum<Status> currentStatus) {
        return null;
    }

    @Override
    public String doing(String activityId, Enum<Status> currentStatus) {
        return null;
    }
}
