package org.apache.activemq.recipes;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;

//public class RequesterExample implements MessageListener  {
public class RequesterExample {

    private final String connectionUri = "tcp://localhost:61616";
    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination destination;
    private static final int NUM_REQUESTS = 1000;
    private final CountDownLatch done = new CountDownLatch(NUM_REQUESTS);

    public void before() throws JMSException {
        connectionFactory = new ActiveMQConnectionFactory(connectionUri);
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue("REQUEST.QUEUE");
    }

    public void after() throws JMSException {
        if (connection != null) {
            connection.close();
        }
    }

    public void run() throws JMSException, InterruptedException {
        TemporaryQueue responseQ = session.createTemporaryQueue();
        MessageProducer requesterProducer = session.createProducer(destination);
        MessageConsumer responseConsumer = session.createConsumer(responseQ);
        //responseConsumer.setMessageListener(this);
        GreatListener responseListener = new GreatListener(done);
        responseConsumer.setMessageListener(responseListener);

        for (int i = 0; i < NUM_REQUESTS; i++) {
            TextMessage requestMessage = session.createTextMessage("Job Request");
            requestMessage.setJMSReplyTo(responseQ);
            requestMessage.setJMSCorrelationID("request: " + i);
            System.out.println("Just sent: " + requestMessage.getText());
            requesterProducer.send(requestMessage);
        }

        if (done.await(10, TimeUnit.MINUTES)) {
            System.out.println("Woohoo! Work's is all done!");
        } else {
            System.out.println("Doh!! Work didn't get done.");
        }

    }

    /*
    public void onMessage(Message message) {
        try {
            String jmsCorrelation = message.getJMSCorrelationID();
            if (!jmsCorrelation.startsWith("request")){
                System.out.println("Received an unexpected response : " +jmsCorrelation);
            }
            TextMessage txtResponse = (TextMessage) message;
            //System.out.println("Requester received: " +txtResponse);
            System.out.println("Have received :" + txtResponse.getJMSCorrelationID() + " from: " + txtResponse.getStringProperty("responder"));
            done.countDown();
        } catch(Exception e){

        }

    }
    */

    public static void main(String[] args) {
        RequesterExample example = new RequesterExample();
        System.out.println("\n\n\n");
        System.out.println("Starting requester example now");
        try {
            example.before();
            example.run();
            example.after();
        } catch (Exception e) {
            System.out.println("Caught an exception during the example: " + e.getMessage());
        }
        System.out.println("Finished runing the Requester example.");
        System.out.println("\n\n\n");

    }
}
