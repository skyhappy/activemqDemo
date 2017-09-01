package topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by piaomingjie on 2017/8/20.
 * 用于接受订阅消息
 */
public class CustomMessageTopic {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.1.101:61616");
        Connection connection=connectionFactory.createConnection();
        connection.start();
        final Session session=connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Destination destination=session.createTopic("Toptic");
        MessageConsumer messageConsumer=session.createConsumer(destination);
        Message message=messageConsumer.receive();

        while (message!=null){
            TextMessage textMessage=(TextMessage)messageConsumer.receive();
            session.commit();
            System.out.println("接受消息："+textMessage.getText());
        }
        session.close();
        connection.close();
    }
}
