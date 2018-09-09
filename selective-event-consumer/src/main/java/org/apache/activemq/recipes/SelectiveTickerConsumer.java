package org.apache.activemq.recipes;

import java.util.concurrent.TimeUnit;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class SelectiveTickerConsumer {

    private final String connectionUri = "tcp://localhost:61616";
    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination destination;
    private String selector;

    private void before() throws Exception {
        connectionFactory = new ActiveMQConnectionFactory(connectionUri);
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        destination = session.createTopic("EVENTS.QUOTES");
        selector = System.getProperty("QuoteSel","symbol='GOOG'");

        /**
         * On Systems.Property, you can pass:
         *
         * -DQuoteSel="symbol='AAPL'"
         * -DQuoteSel="symbol='AAPL' OR symbol='MSFT'"
         * -DQuoteSel="symbol='MSFT' AND price >= 700 "
         *
         */
        //

    }

    public void after() throws Exception {
        if (connection != null){
         connection.close();
        }
    }

    public void run() throws Exception {
        System.out.println("Running the example with selector: " +selector);
        MessageConsumer consumerWithSelector = session.createConsumer(destination,selector);
        consumerWithSelector.setMessageListener(new EventListener());
        TimeUnit.MINUTES.sleep(1);
        connection.close();
        consumerWithSelector.close();
    }

    public static void main(String[] args) {
        SelectiveTickerConsumer consumer = new SelectiveTickerConsumer();
        System.out.println("\n\n\n");
        System.out.println("Starting example Selective Stock Ticker Consumer now...");
        try {
            consumer.before();
            consumer.run();
            consumer.after();
        } catch (Exception e){
            System.out.println("Caught an exception during the example " +e.getMessage());
        }
        System.out.println("Finished running the sample Selective Stock Ticker Consumer app.");
        System.out.println("\n\n\n");
    }


}
