package org.apache.activemq.recipes;

import java.util.concurrent.TimeUnit;

import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.MessageConsumer;
import javax.jms.Destination;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ResponderExample {

    private final String connectionUri = "tcp://localhost:61616";
    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination destination;
    private MessageConsumer requestConsumer;

    public void before() throws JMSException{
        connectionFactory= new ActiveMQConnectionFactory(connectionUri);
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue("REQUEST.QUEUE");
        requestConsumer = session.createConsumer(destination);
        requestConsumer.setMessageListener(new ResponderListener(session,session.createProducer(null)));
    }

    public void after() throws JMSException {
        if (connection !=null){
            connection.close();
        }
    }

    public void run() throws InterruptedException {
        TimeUnit.MINUTES.sleep(3);
    }

    public static void main(String[] args) {

        ResponderExample example = new ResponderExample();
        System.out.println("\n\n\n");
        System.out.println("Starting second responder now");
        try {
            example.before();
            example.run();
            example.after();
        } catch (Exception e){
            System.out.println("Caught an exception during the example: " +e.getMessage());
        }
        System.out.println("Finished running the Responder II example");
        System.out.println("\n\n\n");

    }

}
