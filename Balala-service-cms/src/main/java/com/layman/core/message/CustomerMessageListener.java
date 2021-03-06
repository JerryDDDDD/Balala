package com.layman.core.message;

import com.layman.core.service.solr.SearchService;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * @ClassName CustomerMessageListener
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/6/24 17:05
 * @Version 3.0
 **/
public class CustomerMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        ActiveMQTextMessage am = (ActiveMQTextMessage) message;

        try {
            System.out.println("ActiveMq 中的消息是-cms:" + am.getText());

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
