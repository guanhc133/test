package com.finance.test.msg.send.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: guanhc Date: 2017/6/6 ProjectName:test Version:
 */
@Component
@Slf4j
public class MsgSendMqConsumer implements MessageListener{

    public void onMessage(Message message) {
        String msg = new String(message.getBody());
        log.info("call MsgSendMqConsumer.onMessage,msg={}",msg);
        System.out.println(msg);
    }
}
