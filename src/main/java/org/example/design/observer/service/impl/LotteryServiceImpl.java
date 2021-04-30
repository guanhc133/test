package org.example.design.observer.service.impl;

import org.example.design.observer.MinibusTargetService;
import org.example.design.observer.service.AbstractLotteryService;


/**
 *  @author: guanhongcheng
 *  @Date: 2021/4/30 13:22
 *  @Description: 摇号抽象类
 */
public class LotteryServiceImpl extends AbstractLotteryService {

    private MinibusTargetService minibusTargetService = new MinibusTargetService();

    @Override
    protected String doDraw(String uId) {
        // 摇号
        String lottery = minibusTargetService.lottery(uId);
        // 结果
        return lottery;
    }
}
