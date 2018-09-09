package org.apache.activemq.recipes;

import java.util.concurrent.CountDownLatch;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;

public class VirtualDestinationsApp {

    private final String connectionUri = "tcp://localhost:61619";
    private ActiveMQConnectionFactory connectionFactory;
    private BrokerService broker;
    private Connection receiverConnection;
    private Session receiverSession;
    private final CountDownLatch done = new CountDownLatch(1000);

    public void before() throws Exception {
        broker = new BrokerService();
        broker.setPersistent(false);
        broker.addConnector("tcp://0.0.0.0:61619");
        broker.start();

        connectionFactory = new ActiveMQConnectionFactory(connectionUri);
        receiverConnection = connectionFactory.createConnection();
        receiverSession = receiverConnection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        receiverConnection.start();

    }

    public void after() throws Exception {
        try {
            receiverConnection.close();
        } finally {
            broker.stop();
        }
    }

    private void sendMessages() throws Exception {
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createTopic("VirtualTopic.Foo");
        MessageProducer producer = session.createProducer(destination);
        for (int i=0;i<1000;i++){
            producer.send(session.createMessage());
        }
        connection.close();
    }

    public void run () throws Exception {

        Queue queueA = receiverSession.createQueue("Consumer.A.VirtualTopic.Foo");

        VirtualMessageListener listenerA1 = new VirtualMessageListener(done);
        MessageConsumer consumerA1 = receiverSession.createConsumer(queueA);
        consumerA1.setMessageListener(listenerA1);

        VirtualMessageListener listenerA2 = new VirtualMessageListener(done);
        MessageConsumer consumerA2 = receiverSession.createConsumer(queueA);
        consumerA2.setMessageListener(listenerA2);

        Queue queueB = receiverSession.createQueue("Consumer.B.VirtualTopic.Foo");
        VirtualMessageListener listenerB = new VirtualMessageListener(done);
        MessageConsumer consumerB = receiverSession.createConsumer(queueB);
        consumerB.setMessageListener(listenerB);

        sendMessages();
        done.await();

        System.out.println("Queue A Consumer 1 processed : " +listenerA1.getNumReceived() +" Messages");
        System.out.println("Queue A Consumer 2 processed : " +listenerA2.getNumReceived() +" Messages");
        System.out.println("Queue B Consumer processed : " +listenerB.getNumReceived() +" Messages");

    }

    public static void main(String[] args) {
        VirtualDestinationsApp app = new VirtualDestinationsApp();
        System.out.println("Starting Virtual Destinations Example now ...");
        try {
                app.before();
                app.run();
                app.after();
        } catch (Exception e){
            System.out.println("Caught an exception during the example with msg : " +e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Finished running the Virtual Destination example.");
    }


}
