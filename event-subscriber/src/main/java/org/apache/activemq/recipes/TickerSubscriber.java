package org.apache.activemq.recipes;

import java.util.concurrent.TimeUnit;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Topic;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TickerSubscriber {

    private final String connectionUri = "tcp://localhost:61616";
    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination destination;
    //private Topic destination;

    public void before() throws Exception {
        connectionFactory = new ActiveMQConnectionFactory(connectionUri);
        connection = connectionFactory.createConnection();
        // The setter is to make it durable
        //connection.setClientID("PriceConsumer");
        connection.start();
        session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        destination = session.createTopic("EVENTS.QUOTES");
    }

    public void after() throws Exception {
        if (connection != null){
            connection.close();
        }
    }

    public void run() throws Exception {
        MessageConsumer consumer = session.createConsumer(destination);
        //The below constructor is to make it durable
        //MessageConsumer consumer = session.createDurableSubscriber(destination,"DurableConsumer");
        consumer.setMessageListener(new EventListener());
        TimeUnit.MINUTES.sleep(1);
        connection.stop();
        consumer.close();
    }

    public static void main(String[] args) {
        TickerSubscriber subscriber = new TickerSubscriber();
        System.out.println("\n\n\n");
        System.out.println("Starting example Stock Ticket Subscriber now...");
        try {
            subscriber.before();
            subscriber.run();
            subscriber.after();
        } catch (Exception ex){
            System.out.println("Caught an exception during the example: " + ex.getMessage());
        }
        System.out.println("Finished running the sample Stock Ticket Subscriber app.");
        System.out.println("\n\n\n");
    }

}
