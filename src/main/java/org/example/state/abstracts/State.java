package org.example.state.abstracts;


import org.example.state.enums.Status;

/**
 *  @author: guanhongcheng
 *  @Date: 2021/4/29 15:43
 *  @Description: 在整个接⼝中提供了各项状态流转服务的接⼝，例如；活动提审、审核通过、审核拒绝、撤审撤销
 * 等7个⽅法。
 * 在这些⽅法中所有的⼊参都是⼀样的，activityId( 活动ID )、currentStatus( 当前状态 )，只有他们
 * 的具体实现是不同的
 */
public abstract class State {

    /**
     1
     2
     3 * 活动提审
     *
     * @param activityId 活动ID
     * @param currentStatus 当前状态
     * @return 执⾏结果
     */
    public abstract String arraignment(String activityId, Enum<Status>
            currentStatus);
    /**
     * 审核通过
     *
     * @param activityId 活动ID
     * @param currentStatus 当前状态
     * @return 执⾏结果
     */
    public abstract String checkPass(String activityId, Enum<Status>
            currentStatus);
    /**
     * 审核拒绝
     *
     * @param activityId 活动ID
     * @param currentStatus 当前状态
     * @return 执⾏结果
     */
    public abstract String checkRefuse(String activityId, Enum<Status>
            currentStatus);
    /**
     * 撤审撤销
     *
     * @param activityId 活动ID
     * @param currentStatus 当前状态
     * @return 执⾏结果
     */
    public abstract String checkRevoke(String activityId, Enum<Status>
            currentStatus);
    /**
     * 活动关闭
     *
     * @param activityId 活动ID
     * @param currentStatus 当前状态
     * @return 执⾏结果
     */
    public abstract String close(String activityId, Enum<Status>
            currentStatus);
    /**
     * 活动开启
     *
     * @param activityId 活动ID
     * @param currentStatus 当前状态
     * @return 执⾏结果
     */
    public abstract String open(String activityId, Enum<Status>
            currentStatus);
    /**
     * 活动执⾏
     *
     * @param activityId 活动ID
     * @param currentStatus 当前状态
     * @return 执⾏结果
     *
     * 实际操作中 activityId可以类似于为订单号（也可以放置一个对象），根据订单号查询当前状态是否==currentStatus
     * if true 流转下一状态
     */
    public abstract String doing(String activityId, Enum<Status>
            currentStatus);
}
