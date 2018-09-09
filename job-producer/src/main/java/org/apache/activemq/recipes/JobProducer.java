package org.apache.activemq.recipes;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JobProducer {

    private final String connectionURI = "tcp://localhost:61616";
    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination destination;

    public void before() throws Exception{
        connectionFactory = new ActiveMQConnectionFactory(connectionURI);
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue("JOBQ.Work");
    }

    public void after() throws Exception{
        if (connection != null){
            connection.close();
        }
    }

    public void run() throws Exception {

        MessageProducer producer = session.createProducer(destination);

        for (int i =0; i < 1000; ++i){
            TextMessage message = session.createTextMessage("Job number: "+i);
            message.setIntProperty("JobID",i);
            producer.send(message);
            System.out.println("Producer sent Job("+i+")");
        }

        producer.close();

    }

    public static void main(String[] args) {

        JobProducer producer = new JobProducer();
        System.out.println("\n\n\n");
        System.out.println("Starting examle Job Q Producer now");
        try {
            producer.before();
            producer.run();
            producer.after();
        } catch (Exception e){
            System.out.println("Caught an exception during the example: " +e.getMessage());
        }
        System.out.println("Finished running the sample Job Q Producer app.");
        System.out.println("\n\n\n");

    }

}
