package org.apache.activemq.recipes;

import java.util.concurrent.CountDownLatch;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class GreatListener implements MessageListener {

    private final CountDownLatch doner;

    public GreatListener(CountDownLatch doner) {
        this.doner = doner;
    }

    public void onMessage(Message message) {
        try {
            String jmsCorrelation = message.getJMSCorrelationID();
            if (!jmsCorrelation.startsWith("request")) {
                System.out.println("Received an unexpected response : " + jmsCorrelation);
            }
            TextMessage txtResponse = (TextMessage) message;
            //System.out.println("Requester received: " +txtResponse);
            System.out.println("Have received :" + txtResponse.getJMSCorrelationID() + " from: " + txtResponse.getStringProperty("responder"));
            doner.countDown();
        } catch (Exception e) {

        }

    }


}
