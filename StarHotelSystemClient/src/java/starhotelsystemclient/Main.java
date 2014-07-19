/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starhotelsystemclient;

/**
 *
 * @author wang
 */
import ejb.SHSManagerBeanRemote;
import java.util.ArrayList;
import java.util.Scanner;
import javax.ejb.EJB;

public class Main {

    /**
     * @param args the command line arguments
     */
    @EJB
    private static SHSManagerBeanRemote shsmb;

    public Main() {

    }

    public static void main(String[] args) {
        // TODO code application logic here
        Main client = new Main();

        String input;
        int numInput;
        Scanner sc = new Scanner(System.in);

        do {
            client.displayScreen();
            input = sc.nextLine();
            numInput = Integer.parseInt(input);

            if (numInput == 1) {
                client.addType(sc);
            } else if (numInput == 2) {
                client.deleteRoomType(sc);
            } else if (numInput == 3) {
                client.addRoom(sc);
            } else if (numInput == 4) {
                //delete room
                client.deleteRoom(sc);
            } else if (numInput == 5) {
                //view booking
                ArrayList ci = client.viewCheckin();
                ArrayList co = client.viewCheckout();
                ArrayList status = client.viewStatus();
                ArrayList pay = client.viewPayment();
                ArrayList bkid = client.viewBkId();
                
                System.out.println("View Booking");
                
                for (int i =0; i<bkid.size(); i++) {
                    System.out.println("checkin date: " + ci.get(i));
                    System.out.println("checkout date: " + co.get(i));
                    System.out.println("status: " + status.get(i));
                    System.out.println("price: " + pay.get(i));
                    System.out.println("book ref: " + bkid.get(i));
                    
                    
                    System.out.println();
                }
            } else if (numInput == 6) {
                System.out.println("update booking status: ");
                System.out.println("Enter: 0 for checkin, 1 for checkout: ");
                String choice = sc.nextLine();
                
                
                System.out.println("Enter booking ID: ");
                String choiceId = sc.nextLine();
                
                shsmb.updateBooking(choice, choiceId);
                System.out.println("Successfully updated");

                
            } else if (numInput == 7) {
                System.out.println("view service request");
                ArrayList newal0 = shsmb.viewServiceId();
                ArrayList newal = shsmb.viewServiceType();
                ArrayList newal2 = shsmb.viewServiceDesc();
                for (int i =0; i<newal.size(); i++) {
                    System.out.println("Service Id: "+newal0.get(i));
                    System.out.println("Service Type: "+newal.get(i));
                    System.out.println("Service description: "+newal2.get(i));
                    System.out.println();
                }
                
                System.out.println("Enter serivice id to delete it or enter 0 to skip");
                System.out.println("enter: ");
                String serviceid = sc.nextLine();
                
                
                if (serviceid.equals("0")){
                    System.out.println();
                } else {
                shsmb.deleteService(serviceid);
                System.out.println("Successfully deleted");
                System.out.println();
                }
            }

        } while (!input.equals("0"));
    }

    public ArrayList viewBkId() {
        ArrayList al = new ArrayList();
        al = shsmb.viewMyBooking();
        return al;
    }

    public ArrayList viewStatus() {
        ArrayList al = new ArrayList();
        al = shsmb.viewMyBookingStatus();
        return al;
    }

    public ArrayList viewCheckin() {
        ArrayList al = new ArrayList();
        al = shsmb.viewMyBookingCheckin();
        return al;
    }

    public ArrayList viewPayment() {
        ArrayList al = new ArrayList();
        al = shsmb.viewMyBookingPayment();
        return al;
    }

    public ArrayList viewCheckout() {
        ArrayList al = new ArrayList();
        al = shsmb.viewMyBookingCheckout();
        return al;
    }

    public void deleteRoom(Scanner sc) {
        System.out.println("Delete a room");
        System.out.print("Enter Room Number: ");
        String deleteRm = sc.nextLine();
        shsmb.dummyType();
        shsmb.deleteRoom(deleteRm);
        System.out.println("Successfully delete a room");
    }


    
    public void addRoom(Scanner sc) {
        long roomID, typeID;

        System.out.println("Add a room");
        System.out.println("DO you want to add a new type of room or existing type room?");
        System.out.println("Enter n/N for new Type and e/E for existing type");
        System.out.print("Enter choice: ");
        String name = sc.nextLine();

        if (name.equalsIgnoreCase("n")) {
            System.out.print("Enter room type name: ");
            String nameType = sc.nextLine();
            System.out.println();
            System.out.print("Enter type price: ");
            double price = Double.parseDouble(sc.nextLine());
            System.out.println();
            System.out.print("Enter Maximum number of guest: ");
            int numOfGuest = Integer.parseInt(sc.nextLine());

            typeID = shsmb.createRoomType(nameType, numOfGuest, price);

            System.out.print("Enter room number: ");
            int roomNum = Integer.parseInt(sc.nextLine());
            System.out.println();

            roomID = shsmb.createRoom(roomNum);
            System.out.println("created a room");
            shsmb.addRoom();
            shsmb.persist();
//shsmb.associate(roomID, nameType);

            System.out.println("Successfully added a new room");
        } else if (name.equalsIgnoreCase("e")) {
            System.out.print("Enter room type name: ");
            String roomType = sc.nextLine();
            System.out.println();

            //shsmb.changeRoomType(roomType);
            System.out.print("Enter room number: ");
            int roomNum = Integer.parseInt(sc.nextLine());
            System.out.println();
            shsmb.associate(roomNum, roomType);
            System.out.println("Successfully added a new room");
        }
    }

    public void deleteRoomType(Scanner sc) {
        System.out.println("Delete a type");
        System.out.print("Enter type name: ");
        String name = sc.nextLine();
        System.out.println();

        System.out.println(shsmb.deleteRoomType(name));
    }

    public void addType(Scanner sc) {
        System.out.println("Add a type");
        System.out.print("Enter type name: ");
        String nameType = sc.nextLine();
        System.out.println();
        System.out.print("Enter type price: ");
        double price = Double.parseDouble(sc.nextLine());
        System.out.println();
        System.out.print("Enter Maximum number of guest: ");
        int numOfGuest = Integer.parseInt(sc.nextLine());
        System.out.println();

        shsmb.createRoomType(nameType, numOfGuest, price);
        shsmb.persistRoomType();
        System.out.println("Successfully added a new room type");

    }

    public void displayScreen() {
        System.out.println("Select One of the following by its integer value");
        System.out.println("1. Add type of a room");
        System.out.println("2. Delete type of a room");
        System.out.println("3. Add a room");
        System.out.println("4. Delete a room");
        System.out.println("5. View Booking Status");
        System.out.println("6. Update Booking Status");
        System.out.println("7. View Service Request");
        System.out.println();
        System.out.println("0. Exit");
        System.out.println();
        System.out.print("Enter choice: ");

    }

}
