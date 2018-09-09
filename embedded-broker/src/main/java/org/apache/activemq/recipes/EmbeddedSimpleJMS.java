package org.apache.activemq.recipes;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;

public class EmbeddedSimpleJMS {

    private final String connectionUri ="tcp://localhost:61616";
    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination destination;
    private BrokerService service;

    public void before() throws Exception {

        //service = BrokerFactory.createBroker("xbean:activemq.xml");

        service = new BrokerService();
        service.addConnector("tcp://localhost:61616");
        service.setPersistent(false);
        service.start();

        connectionFactory = new ActiveMQConnectionFactory(connectionUri);
        connection = connectionFactory.createConnection();
        connection.start();

        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue("MyQueue");

        System.out.println("I have started this sucker");

    }

    public void after() throws Exception {
        if (connection != null){
            connection.close();
        }

        if (service != null){
            service.stop();
        }
    }

    public void run() throws Exception {

        MessageProducer producer = session.createProducer(destination);
        try {
            TextMessage message = session.createTextMessage();
            message.setText("We sent a message");
            producer.send(message);
            System.out.println("I sent " +message.getText());
        } finally {
            producer.close();
        }

        MessageConsumer consumer = session.createConsumer(destination);
        try {
            TextMessage message = (TextMessage)consumer.receive();
            System.out.println("I received " +message.getText());
        } finally {
            consumer.close();
        }

    }

    public static void main(String[] args) {

        EmbeddedSimpleJMS example = new EmbeddedSimpleJMS();
        try {
            example.before();
            example.run();
            example.after();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
