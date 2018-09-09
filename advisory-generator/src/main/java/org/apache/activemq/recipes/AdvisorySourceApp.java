package org.apache.activemq.recipes;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Message;

import org.apache.activemq.ActiveMQConnectionFactory;

public class AdvisorySourceApp implements Runnable {

    private final String connectionUri = "tcp://localhost:61616";
    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination destination;
    private final Random rand = new Random();

    public void run() {

        try {
            connectionFactory = new ActiveMQConnectionFactory(connectionUri);
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("MyQueue");

            MessageProducer producer = session.createProducer(destination);
            Message myMessage = session.createMessage();
            producer.send(session.createMessage());

            TimeUnit.SECONDS.sleep(rand.nextInt(10));

            MessageConsumer consumer = session.createConsumer(destination);
            consumer.receive();

            TimeUnit.SECONDS.sleep(rand.nextInt(10));

            System.out.print(".");

            TimeUnit.SECONDS.sleep(rand.nextInt(10));

            if (connection != null){
                connection.close();
            }

        } catch (Exception e){
            System.out.println("Something went off on " +AdvisorySourceApp.class.getName() + " with msg: " +e.getMessage());
        }

    }

    public static void main(String[] args) {

        System.out.println("Starting Advisory Message Generation app now...");
        try {

            ExecutorService service = Executors.newFixedThreadPool(2);
            for (int i=0 ; i <2; i ++){
                service.execute(new AdvisorySourceApp());
            }

            service.shutdown();
            service.awaitTermination(3,TimeUnit.MINUTES);
            System.out.println();

        } catch(Exception e){
            System.out.println("Caught an exception during the example " +e.getMessage());
        }
        System.out.println("Finished running the Advisory Message Generation application.");

    }
}
