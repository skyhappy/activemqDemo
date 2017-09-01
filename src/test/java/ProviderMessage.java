import com.sun.org.apache.xpath.internal.SourceTree;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by piaomingjie on 2017/8/9.
 * 我是消息生产者，负责生产消息
 */

public class ProviderMessage {
    public static void main(String[] args) throws JMSException {
        System.out.println("这是一个生产者");
        /**创建工厂*/
        /**1根据ip获得链接*/
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.31.163:61616");
        /**2.创建链接*/
        Connection connection=connectionFactory.createConnection();
        /**3.开始这个链接*/
        connection.start();
        /**5.创建session*/
        Session  session=connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);//回话自动确认
        /**6.创建目的地*/
        Destination destination=session.createQueue("my-queue");
        /**创建消息生产者*/
        MessageProducer messageProducer=session.createProducer(destination);
        for (int i=0;i<10;i++){
            /**发送消息*/
            TextMessage textMessage=session.createTextMessage("message"+i+1);
            messageProducer.send(textMessage);
        }
        /**提交session*/
        session.commit();
        session.close();
        connection.close();
    }
}
