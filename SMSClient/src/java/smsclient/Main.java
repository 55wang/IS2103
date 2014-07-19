/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smsclient;

/**
 *
 * @author wang
 */
import ejb.SMSManagerBeanRemote;
import javax.ejb.EJB;
import java.util.Scanner;

/**
 *
 * @author wang
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    @EJB
    private static SMSManagerBeanRemote smsmb;

    public Main() {

    }

    public static void main(String[] args) {
        // TODO code application logic here

        Main sms = new Main();
        sms.display();
    }

    public void display() {
        try {
            String serviceType;
            String desc;
            String input;
            String roomNumber;
            Scanner sc = new Scanner(System.in);

            do {
                System.out.println("SHS SERVICE REQUST SERVICE: ENTER y/Y to procees or q/Q to quit");

                input = sc.nextLine();

                if (input.equalsIgnoreCase("y")) {

                    System.out.println("Enter your room number");
                    roomNumber = sc.nextLine();
                    System.out.println("Enter the service type: food/clean/maintenance ");
                    serviceType = sc.nextLine();
                    System.out.println("Enter the description: ");
                    desc = sc.nextLine();
                    System.out.println(roomNumber + serviceType + desc);

                    String temp = roomNumber + "%%" + serviceType + "%%" + desc;
                    smsmb.print(temp);
                    
                    System.out.println("Message successfully sent");
                    System.out.println();
                } else if (input.equalsIgnoreCase("q")) {
                    break;
                } else {
                    System.out.println("Wrong input, restart program");
                }

            } while (input.equalsIgnoreCase("y"));
            System.out.println("SMS application is closed");
            sc.close();
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
    }

}
