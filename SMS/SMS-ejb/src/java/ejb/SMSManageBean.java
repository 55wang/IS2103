/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.annotation.Resource;
import javax.ejb.Stateful;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 *
 * @author wang
 */
@Stateful
public class SMSManageBean implements SMSManagerBeanRemote {

    @Resource(mappedName = "jms/NewMessageFactory")
    private ConnectionFactory ConnectionFactory;
    @Resource(mappedName = "jms/NewMessage")
    private Queue queue;

    Connection Connection = null;
    Session session = null;
    MessageProducer producer = null;
    TextMessage message = null;

    public void print(String temp) {
        System.out.println("sms: " + temp);
        try {

            Connection = ConnectionFactory.createConnection();
            session = Connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            System.out.println("reach here");
            producer = session.createProducer(queue);

            message = session.createTextMessage();
            message.setText(temp);

            System.out.println("Sending: " + message.getText());
            producer.send(message);
            producer.close();
            Connection.close();
        } catch (Exception e) {
            System.err.println("SMS: Exception: "
                    + e.toString());
        }
    }

}
