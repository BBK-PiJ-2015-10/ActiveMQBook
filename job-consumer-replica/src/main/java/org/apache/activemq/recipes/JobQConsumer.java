package org.apache.activemq.recipes;

import java.util.concurrent.TimeUnit;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JobQConsumer {

    private final String connectionURI = "tcp://localhost:61616?jms.prefetchPolicy.queuePrefetch=1";
    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination destination;

    public void before() throws Exception {
        connectionFactory = new ActiveMQConnectionFactory(connectionURI);
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue("JOBQ.Work");
    }

    public void after() throws Exception {
        if (connection != null){
            connection.close();
        }
    }

    public void run() throws Exception {
        MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener(new JobQListener());
        TimeUnit.MINUTES.sleep(1);
        connection.stop();
        consumer.close();
    }

    public static void main(String[] args) {
        JobQConsumer jconsumer = new JobQConsumer();
        System.out.println("\n\n\n");
        System.out.println("Starting example consumer now...");
        try {
            jconsumer.before();
            jconsumer.run();
            jconsumer.after();
        } catch (Exception e){
            System.out.println("Caught an exception during the example" +e.getMessage());
        }
        System.out.println("Finished running the sample Consumer app.");
        System.out.println("\n\n\n");
    }

}
