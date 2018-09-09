package org.apache.activemq.recipes;

import java.util.concurrent.TimeUnit;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class FailoverConsumer {

    private final String connectionUri = "failover:tcp://localhost:61616";
    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination destination;

    public void before() throws Exception {
        connectionFactory = new ActiveMQConnectionFactory(connectionUri);
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue("MyQueue");
    }

    public void after() throws Exception {
        if (connection!=null){
            connection.close();
        }
    }

    public void run() throws Exception {
        MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener(new EventListener());
        TimeUnit.MINUTES.sleep(3);
        consumer.close();
    }

    public static void main(String[] args) {
        FailoverConsumer consumer = new FailoverConsumer();
        System.out.println("Starting example Failover Consumer now...");
        try {
            consumer.before();
            consumer.run();
            consumer.after();
        } catch (Exception e){
            System.out.println("This exception was thrown " +e.getMessage());
        }
        System.out.println("Finished running failover consumer");
    }

}
