/**
 * Created by piaomingjie on 2017/8/9.
 */

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/***消息接受着*/
public class QueueReceiver {

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.31.163:61616");
        Connection connection=connectionFactory.createConnection();
        connection.start();
        final Session session=connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        /**从目的地获取消息队列*/
        Destination destination=session.createQueue("my-queue");
        /**消息消费者*/
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
