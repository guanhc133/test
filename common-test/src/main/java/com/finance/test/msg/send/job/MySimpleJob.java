package com.finance.test.msg.send.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.finance.test.msg.send.mapper.UserInfoMapper;
import com.finance.test.msg.send.model.UserInfo;
import com.finance.test.msg.send.util.util.DateUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/************************************************************************************
 * Date                Editor
 * 2018/7/27            官红诚         Create
 ************************************************************************************/
@Service
@Slf4j
public class MySimpleJob implements SimpleJob {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("补偿前一天已竣工未结清但没网联签约记录的订单，日志号：{}");
        //分片项
        int shardingItem = shardingContext.getShardingItem();
        //分片总数
        int shardingTotalCount = shardingContext.getShardingTotalCount();
        //开始时间
        Date startDate = DateUtil.plusDays(DateUtil.getCurrentDate(DateUtil.fullPattern), -1);
        //截止时间
        Date endDate = DateUtil.getCurrentDate(DateUtil.fullPattern);
        //序列id
        String id = "0";
        List<String> signIds = Lists.newArrayList();
        //1.分页查询数据库中签约失败状态的数据
        while (true) {
            //查询前一天已竣工未结清订单
            List<UserInfo> resultList = userInfoMapper.querySpecialTinme(shardingItem, startDate, shardingTotalCount);
            for (UserInfo result : resultList) {
                if (signIds.contains(result.getId())) {
                    continue;
                }
                //处理业务
            }
            signIds.clear();
            //分页查询sql，查询条件还款开始时间通过传入的开始时间（不包括）至截止时间（包括），查询最新的数据
            List<UserInfo> orderList = userInfoMapper.queryTimeAll(shardingItem, startDate, "500", shardingTotalCount, endDate, id);
            if (orderList != null && !orderList.isEmpty()) {
                //需要
//                startDate = orderList.get(orderList.size() - 1).getCompleteTime();
//                id = orderList.get(orderList.size() - 1).getId();
                for (UserInfo result : orderList) {
                    // TODO　处理业务
//                    signIds.add(result.getId());
                }
            } else {
                break;
            }
        }
    }
}
