package org.apache.activemq.recipes;

import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.jms.Message;
import javax.jms.JMSException;
import javax.jms.Destination;
import javax.jms.Session;
import javax.jms.MessageProducer;

public class ResponderListener implements MessageListener {

    private Session session;

    private MessageProducer responder;

    public ResponderListener(Session session, MessageProducer responder) {
        this.session = session;
        this.responder = responder;
    }

    public void onMessage(Message message) {
        try {
            Destination replyToDestination = message.getJMSReplyTo();
            if (replyToDestination != null) {
                TextMessage receivedTxtMsg = (TextMessage) message;
                System.out.println("I have received : " + receivedTxtMsg);
                Message responseMsg = session.createTextMessage("Job Finished");
                responseMsg.setJMSCorrelationID(receivedTxtMsg.getJMSCorrelationID());
                responseMsg.setStringProperty("responder","jms-second-with-listener");
                responder.send(replyToDestination, responseMsg);
            }
        } catch (Exception e) {
            System.out.println("Encountered an error while responding: " + e.getMessage());
        }

    }

}
