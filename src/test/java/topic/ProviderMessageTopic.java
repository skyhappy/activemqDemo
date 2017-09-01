package topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by piaomingjie on 2017/8/20.
 * 这是一个消息队列生产者
 */
public class ProviderMessageTopic {
    public static void main(String[] args) throws JMSException {
            /*1 创建工厂*/
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.1.101:61616");
        Connection connection=connectionFactory.createConnection();//创建连接
        connection.start();//开始连接
        Session session=connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);//回话自动确认
        //创建订阅
        Destination destination=session.createTopic("Toptic");
        //创建消息生产者
        MessageProducer messageProducer=session.createProducer(destination);
        for (int i=0;i<10;i++){
             TextMessage textMessage=session.createTextMessage("message0"+i);
            messageProducer.send(textMessage);
        }
        session.commit();
        session.close();
        connection.close();
    }

}
