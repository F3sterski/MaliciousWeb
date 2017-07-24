package org.apache.struts.maliweb.producer;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.struts.maliweb.model.Malicious;
import org.apache.struts.maliweb.model.ReplaceMalice;

import javax.jms.*;

/**
 * Created by szkol_000 on 21.01.2017.
 */
public class MaliciousProducer implements Runnable{

    private final String separateSign = ";";

    private String adres = "";

    public MaliciousProducer(Malicious malice) {
        this.malice = malice;
    }

    Malicious malice;

    public MaliciousProducer(Malicious malicious, String mqAdres) {
        this.malice = malicious;
        this.adres = mqAdres;
    }

    public MaliciousProducer(ReplaceMalice malicious, String mqAdres) {
        this.malice = malicious;
        this.adres = mqAdres;
    }

    public Malicious getMalice() {
        return malice;
    }

    public void setMalice(Malicious malice) {
        this.malice = malice;
    }

    @Override
    public void run() {
        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(adres);
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("TEST.FOO");

            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            String text;

            System.out.println(this.getMalice() instanceof ReplaceMalice);

            if(this.getMalice() instanceof ReplaceMalice){
                ReplaceMalice r = (ReplaceMalice)this.getMalice();
                text = r.getName() + separateSign + r.getFromString() + separateSign + r.getToString();
            }else{
                text = this.getMalice().getName() + separateSign + this.getMalice().getTime();
            }
            TextMessage message = session.createTextMessage(text);

            System.out.println("Sent message: "+ text);
            producer.send(message);

            session.close();
            connection.close();
        }
        catch (Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
    }
}
