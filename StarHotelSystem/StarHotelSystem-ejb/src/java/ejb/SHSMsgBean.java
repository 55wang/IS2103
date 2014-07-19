/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author wang
 */
@MessageDriven(mappedName = "jms/NewMessage", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
})

public class SHSMsgBean implements MessageListener {
    
    @Resource
    private MessageDrivenContext mdc;
    
    @PersistenceContext
    private EntityManager em;
    
    public SHSMsgBean() {
    }
    
    public void onMessage(Message inMessage) {
        TextMessage msg = null;
        try {

            if (inMessage instanceof TextMessage) {
                System.out.println("onMessage received in SHSMessageBean.java");
                msg = (TextMessage) inMessage;
                Thread.sleep(100);
                processing(msg);
            } else {
                System.out.println("SHSMessageBean.onMessage:" + " Message of wrong type: " + inMessage.getClass().getName());
            }
        } catch (Exception ex){
            System.out.println("SHS_MessageBean.onMessage: "+
                               "Exception: "+ex.toString());
        } 

    }

    private void processing(TextMessage msg) throws JMSException, ParseException {
       
        
        System.out.println("processing in SHSMessageBean starts...");
        String tempMsg = msg.getText();

        String[] temp = tempMsg.split("%%");
        String roomNumber = temp[0];
        String serviceType = temp[1];
        String description = temp[2];
System.out.println("this is : room Number: "+roomNumber);
        try {
            
            Query q = em.createQuery("SELECT t from ServiceEntity t");
        for (Object o : q.getResultList()) {
            ServiceEntity p = (ServiceEntity) o;
            if (p.getRooms().getRoomNumber()==Integer.parseInt(roomNumber)){
                if (p.getServiceType().equals(serviceType)) {
                    System.out.println("SERVICE: duplicated service found");
                    return;
                }
            }
        }
            
            
            createService(serviceType, description, roomNumber);
        } catch (IllegalArgumentException iae) {
            System.out.println("MessageDrivenBean1.setUpEntities: "
                    + "No entity found");
        } catch (Exception e) {
            System.out.println("MessageDrivenBean1.setUpEntities: "
                    + "em.find failed without throwing "
                    + "IllegalArgumentException");
        }
    }

    public void createService(String serviceType, String description, String roomNumber) throws ParseException {
        //finding roomnumber
        int rmNumber = Integer.parseInt(roomNumber);

        Query q = em.createQuery("SELECT t from RoomEntity t");
        for (Object o : q.getResultList()) {
            RoomEntity p = (RoomEntity) o;

            if (p.getRoomNumber() == rmNumber) {
                ServiceEntity se = new ServiceEntity();
                Date time = new Date();
                SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
                se.create(ft.format(time), serviceType, description);
                se.setRooms(p);
                em.merge(se);

            }
        }
       // System.out.println("room number not found");
    }

}
