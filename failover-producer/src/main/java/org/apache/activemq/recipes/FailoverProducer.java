package org.apache.activemq.recipes;

import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.concurrent.TimeUnit;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Session;
import javax.jms.Message;
import javax.jms.MessageProducer;

public class FailoverProducer {

    private final String connectionUri = "failover:tcp://localhost:61616";
    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination destination;

    private void before () throws Exception {
        connectionFactory = new ActiveMQConnectionFactory(connectionUri);
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue("MyQueue");
    }

    private void after () throws Exception {
        if (connection != null){
            connection.close();
        }
    }

    public void run () throws Exception {
        MessageProducer producer = session.createProducer(destination);
        for (int i=1; i<=1000;i++){
            Message message = session.createMessage();
            message.setIntProperty("num",i);
            producer.send(message);
            System.out.println("Failover producer sent Message #" +i);
            TimeUnit.SECONDS.sleep(1);
        }
        producer.close();
    }

    public static void main(String[] args) {
        FailoverProducer producer = new FailoverProducer();
        System.out.println("Starting failover producer now");
        try {
            producer.before();
            producer.run();
            producer.after();
        } catch(Exception e){
            System.out.println("Caught an exception during the example : " +e.getMessage());
        }
    }




}
