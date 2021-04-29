package org.example;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.state.enums.Status;
import org.example.state.handler.StateHandler;
import org.example.state.service.ActivityService;
import org.junit.Test;

@Slf4j
public class StateTest {

    @Test
    public void EditingToArraignment() {
        String activityId = "100001";
        ActivityService.init(activityId, Status.Editing);
        StateHandler stateHandler = new StateHandler();
        String result = stateHandler.arraignment(activityId, Status.Editing);
        System.out.println("测试结果(编辑中To提审活动)：" + result);
        System.out.println("活动信息：" + JSON.toJSONString(ActivityService.queryActivityInfo(activityId))
                + " 状态：" + JSON.toJSONString(ActivityService.queryActivityInfo(activityId).getStatus()
        ));
    }


}
