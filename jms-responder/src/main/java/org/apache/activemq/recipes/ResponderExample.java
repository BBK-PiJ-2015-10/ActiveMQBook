package org.apache.activemq.recipes;

import java.util.concurrent.TimeUnit;

import javax.jms.*;
import javax.jms.MessageListener;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ResponderExample implements MessageListener {

    private final String connectionUri = "tcp://localhost:61616";
    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination destination;
    private MessageConsumer requestListener;
    private MessageProducer responder;

    public void before() throws JMSException {
        connectionFactory = new ActiveMQConnectionFactory(connectionUri);
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue("REQUEST.QUEUE");
        // Made it null, to be anonymous
        responder = session.createProducer(null);
        requestListener = session.createConsumer(destination);
        requestListener.setMessageListener(this);
    }

    public void after() throws JMSException {
        if (connection != null) {
            connection.close();
        }
    }

    public void run() throws InterruptedException {
        TimeUnit.MINUTES.sleep(3);
    }

    public void onMessage(Message message) {

        try {
            Destination replyTo = message.getJMSReplyTo();
            if (replyTo != null) {
                TextMessage txtMsg = (TextMessage) message;
                System.out.println("I have received  : " + txtMsg);
                Message response = session.createTextMessage("Job Finished");
                response.setJMSCorrelationID(message.getJMSCorrelationID());
                response.setStringProperty("responder","jms-responder");
                responder.send(replyTo, response);
            }
        } catch (JMSException e) {
            System.out.println("Encounted an error while responding: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        ResponderExample example = new ResponderExample();
        System.out.println("\n\n\n");
        System.out.println("Starting responder now...");
        try {
            example.before();
            example.run();
            example.after();
        } catch (Exception e) {
            System.out.println("Caught an exception during the example: " + e.getMessage());
        }
        System.out.println("Finished running the Responder example.");
        System.out.println("\n\n\n");

    }


}
