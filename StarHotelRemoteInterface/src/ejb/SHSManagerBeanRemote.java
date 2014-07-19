/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.ArrayList;
import java.util.Date;
import javax.ejb.Remote;

/**
 *
 * @author wang
 */
@Remote
public interface SHSManagerBeanRemote {

    public void remove();

    public long createRoomType(String nameType, int numOfGuest, double price);

    public String deleteRoomType(String roomType);

    public long createRoom(int roomNumber);

    public void associate(int roomNumber, String type);

    public void findRoomType(String roomType);

    public void persist();

    public void persistRoomType();

    public void addRoom();

    public boolean createMember(String passport, String name, String password, String postal, int phone, String email);

    public boolean loginMember(String passport, String password);

    public boolean getLogin();

    public void logout();

    public ArrayList searchRoomNumber(Date checkin, Date checkout);

    public ArrayList searchRoomType(ArrayList data);

    public ArrayList searchCapacity(ArrayList data);

    public ArrayList searchPrice(ArrayList data);

    public ArrayList shoppingCartt();
    
    public long shoppingCart(ArrayList temp, Date checkin, Date checkout);
    
    public void makePayment(long id);

    public ArrayList viewBooking();

    public ArrayList viewBookingPayment();

    public ArrayList viewBookingStatus();

    public ArrayList viewBookingCheckout();

    public ArrayList viewBookingCheckin();

    public ArrayList viewMyBooking();

    public ArrayList viewMyBookingPayment();

    public ArrayList viewMyBookingStatus();

    public ArrayList viewMyBookingCheckout();

    public ArrayList viewMyBookingCheckin();
    
    public void dummyType();
            
    public void deleteRoom(String temp);

    public void updateBooking(String choice, String choiceId);

    public ArrayList viewServiceType();

    public ArrayList viewServiceDesc();

    public ArrayList viewServiceId();

    public void deleteService(String id);
}
