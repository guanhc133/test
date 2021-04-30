package org.example.design.observer.listener.impl;

import org.example.design.observer.listener.EventListener;


/**
 *  @author: guanhongcheng
 *  @Date: 2021/4/30 11:24
 *  @Description: 消息监听
 */
public class MessageEventListener implements EventListener {
    @Override
    public void doEvent(String result) {
        System.out.println("触发发送短信动作，消息：" + result);
    }
}
