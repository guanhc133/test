package org.example.design.observer.manager;


import org.example.design.observer.listener.EventListener;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: guanhongcheng
 * @Date: 2021/4/30 11:27
 * @Description: 事件管理
 */
public class EventManager {

    Map<Enum<EventType>, List<EventListener>> listeners = new HashMap<>();

    public EventManager(Enum<EventType>... operations) {
        for (Enum<EventType> operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    public enum EventType {
        MQ, Message
    }

    /**
     * 订阅
     *
     * @param eventType 事件类型
     * @param listener  监听
     */
    public void subscribe(Enum<EventType> eventType, EventListener
            listener) {
        List<EventListener> users = listeners.get(eventType);
        users.add(listener);
    }

    /**
     * 取消订阅
     *
     * @param eventType 事件类型
     * @param listener  监听
     */
    public void unsubscribe(Enum<EventType> eventType, EventListener
            listener) {
        List<EventListener> users = listeners.get(eventType);
        listeners.remove(eventType);
        users.remove(listener);
    }

    /**
     * 通知
     *
     * @param eventType 事件类型
     * @param result    结果
     */
    public void notify(Enum<EventType> eventType, String result) {
        List<EventListener> users = listeners.get(eventType);
        if (!CollectionUtils.isEmpty(users)) {
            for (EventListener listener : users) {
                listener.doEvent(result);
            }
        }
    }

}
