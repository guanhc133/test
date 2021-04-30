package org.example.design.state.handler;


import org.example.design.state.abstracts.*;
import org.example.design.state.enums.Status;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 活动状态变更
 * 1. 编辑中 -> 提审、关闭
 * 2. 审核通过 -> 拒绝、关闭、活动中
 * 3. 审核拒绝 -> 撤审、关闭
 * 4. 活动中 -> 关闭
 * 5. 活动关闭 -> 开启
 * 6. 活动开启 -> 关闭
 * @return 返回结果
 */
/**
 * @author: guanhongcheng
 * @Date: 2021/4/29 15:46
 * @Description:
 * 状态处理服务
 * 这是对状态服务的统⼀控制中⼼，可以看到在构造函数中提供了所有状态和实现的具体关联，放到
 * Map数据结构中。
 * 同时提供了不同名称的接⼝操作类，让外部调⽤⽅可以更加容易的使⽤此项功能接⼝，⽽不需要像
 * 在 itstack-demo-design-19-01 例⼦中还得传两个状态来判断
 */
public class StateHandler {

    private Map<Enum<Status>, State> stateMap = new
            ConcurrentHashMap<Enum<Status>, State>();

    public StateHandler() {
        // 待审核
        stateMap.put(Status.Check, new CheckState());
        // 已关闭
        stateMap.put(Status.Close, new CloseState());
        // 活动中
        stateMap.put(Status.Doing, new DoingState());
        // 编辑中
        stateMap.put(Status.Editing, new EditingState());
        // 已开启
        stateMap.put(Status.Open, new OpenState());
        // 审核通过
        stateMap.put(Status.Pass, new PassState());
        // 审核拒绝
        stateMap.put(Status.Refuse, new RefuseState());
    }

    public String arraignment(String activityId, Enum<Status>
            currentStatus) {
        return stateMap.get(currentStatus).arraignment(activityId,
                currentStatus);
    }

    public String checkPass(String activityId, Enum<Status> currentStatus) {
        return stateMap.get(currentStatus).checkPass(activityId,
                currentStatus);
    }

    public String checkRefuse(String activityId, Enum<Status>
            currentStatus) {
        return stateMap.get(currentStatus).checkRefuse(activityId,
                currentStatus);
    }

    public String checkRevoke(String activityId, Enum<Status>
            currentStatus) {
        return stateMap.get(currentStatus).checkRevoke(activityId,
                currentStatus);
    }

    public String close(String activityId, Enum<Status> currentStatus) {
        return stateMap.get(currentStatus).close(activityId,
                currentStatus);
    }

    public String open(String activityId, Enum<Status> currentStatus) {
        return stateMap.get(currentStatus).open(activityId,
                currentStatus);
    }

    public String doing(String activityId, Enum<Status> currentStatus) {
        return stateMap.get(currentStatus).doing(activityId,
                currentStatus);
    }

}
