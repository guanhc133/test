package org.example;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.design.state.enums.Status;
import org.example.design.state.handler.StateHandler;
import org.example.design.state.service.ActivityService;
import org.junit.Test;

@Slf4j
public class StateTest {

    @Test
    public void EditingToArraignment() {
        String activityId = "100001";
        //当前状态
        ActivityService.init(activityId, Status.Editing);
        StateHandler stateHandler = new StateHandler();
        //调用待变更状态对应的接口 arraignment
        String result = stateHandler.arraignment(activityId, Status.Editing);
        System.out.println("测试结果(编辑中To提审活动)：" + result);
        System.out.println("活动信息：" + JSON.toJSONString(ActivityService.queryActivityInfo(activityId))
                + " 状态：" + JSON.toJSONString(ActivityService.queryActivityInfo(activityId).getStatus()
        ));
    }

    @Test
    public void ArraignmentToDoing() {
        String activityId = "100001";
        ActivityService.init(activityId, Status.Check);
        StateHandler stateHandler = new StateHandler();
        String result = stateHandler.doing(activityId, Status.Check);
        System.out.println("测试结果(审核中To活动中)：" + result);
        System.out.println("活动信息：" + JSON.toJSONString(ActivityService.queryActivityInfo(activityId))
                + " 状态：" + JSON.toJSONString(ActivityService.queryActivityInfo(activityId).getStatus()
        ));
    }


}
