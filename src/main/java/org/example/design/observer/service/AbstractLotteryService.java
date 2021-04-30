package org.example.design.observer.service;

import org.example.design.observer.listener.impl.MQEventListener;
import org.example.design.observer.listener.impl.MessageEventListener;
import org.example.design.observer.manager.EventManager;


/**
 *  @author: guanhongcheng
 *  @Date: 2021/4/30 13:21
 *  @Description: 摇号抽象类
 */
public abstract class AbstractLotteryService {

    private EventManager eventManager;

    public AbstractLotteryService() {
        eventManager = new EventManager(EventManager.EventType.MQ,
                EventManager.EventType.Message);
        eventManager.subscribe(EventManager.EventType.MQ, new
                MQEventListener());
        eventManager.subscribe(EventManager.EventType.Message, new
                MessageEventListener());
    }

    public String draw(String uId) {
        String lotteryResult = doDraw(uId);
        // 需要什么通知就给调⽤什么⽅法
        eventManager.notify(EventManager.EventType.MQ, lotteryResult);
        eventManager.notify(EventManager.EventType.Message,
                lotteryResult);
        return lotteryResult;
    }

    protected abstract String doDraw(String uId);
}
