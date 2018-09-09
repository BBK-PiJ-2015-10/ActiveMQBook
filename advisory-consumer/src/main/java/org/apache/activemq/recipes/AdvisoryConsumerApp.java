package org.apache.activemq.recipes;

import java.util.concurrent.TimeUnit;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.advisory.AdvisorySupport;

public class AdvisoryConsumerApp implements MessageListener {

    private final String connectionUri = "tcp://localhost:61616";
    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination destination;
    private MessageConsumer advisoryConsumer;
    private Destination monitored;

    public void before() throws JMSException{
        connectionFactory = new ActiveMQConnectionFactory(connectionUri);
        connection = connectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        monitored = session.createQueue("MyQueue");
        destination = session.createTopic(AdvisorySupport.getConsumerAdvisoryTopic(monitored).getPhysicalName()
        +","+ AdvisorySupport.getProducerAdvisoryTopic(monitored).getPhysicalName());
        advisoryConsumer = session.createConsumer(destination);
        advisoryConsumer.setMessageListener(this);
        connection.start();
    }

    public void after()throws JMSException{
        if (connection != null){
            connection.close();
        }
    }

    public void run() throws Exception{
        TimeUnit.MINUTES.sleep(5);
    }

    public void onMessage(Message message) {
        try {
            Destination source = message.getJMSDestination();
            if (source.equals(AdvisorySupport.getConsumerAdvisoryTopic(monitored))){
                int consumerCount = message.getIntProperty("consumerCount");
                System.out.println("New Consumer Advisory, Consumer Count: " +consumerCount);
            } else if (source.equals(AdvisorySupport.getProducerAdvisoryTopic(monitored))) {
                int producerCount = message.getIntProperty("producerCount");
                System.out.println("New Producer Advisory, Producer count: " + producerCount);
            }
        } catch(JMSException ex) {

        }
    }

    public static void main(String[] args) {
        AdvisoryConsumerApp app = new AdvisoryConsumerApp();
        System.out.println("Starting Advisory Consumer example now ...");
        try {
            app.before();
            app.run();
            app.after();
        } catch(Exception e){
            System.out.println("Caught an exception during the example");
        }
        System.out.println("Finished running advisory consumer example");
    }
}
