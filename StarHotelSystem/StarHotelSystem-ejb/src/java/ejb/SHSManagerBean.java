/*
 * PersonAddressManagerBean.java
 */
package ejb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateful
public class SHSManagerBean implements SHSManagerBeanRemote {

    @PersistenceContext
    EntityManager em;

    private Collection<RoomEntity> rooms;
    private TypeEntity typeEntity;
    private RoomEntity roomEntity;
    private Collection<BookingEntity> booking;
    private boolean isLogin = false;
    private String username;
    private long userId;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public SHSManagerBean() {
    }

    public ArrayList viewServiceId() {
        ArrayList temp = new ArrayList();
        Query q = em.createQuery("SELECT t from ServiceEntity t");
        for (Object o : q.getResultList()) {
            ServiceEntity p = (ServiceEntity) o;

            temp.add(p.getId());
        }
        return temp;
    }

    public ArrayList viewServiceType() {
        ArrayList temp = new ArrayList();
        Query q = em.createQuery("SELECT t from ServiceEntity t");
        for (Object o : q.getResultList()) {
            ServiceEntity p = (ServiceEntity) o;

            temp.add(p.getServiceType());
        }
        return temp;
    }

    public ArrayList viewServiceDesc() {
        ArrayList temp = new ArrayList();
        Query q = em.createQuery("SELECT t from ServiceEntity t");
        for (Object o : q.getResultList()) {
            ServiceEntity p = (ServiceEntity) o;

            temp.add(p.getDescription());
        }
        return temp;
    }

    public void deleteService(String id) {
        long tempid = Long.parseLong(id);
        Query q = em.createQuery("SELECT t from ServiceEntity t");
        for (Object o : q.getResultList()) {
            ServiceEntity p = (ServiceEntity) o;
            if (p.getId() == tempid) {

                p.setRooms(null);
                em.merge(p);
                em.flush();

                em.remove(p);
                em.flush();
                break;
            }
        }
    }

    public void deleteRoom(String temp) {
        int rm = Integer.parseInt(temp);
        TypeEntity tempTE = null;

        Query g = em.createQuery("SELECT t from TypeEntity t");
        for (Object o : g.getResultList()) {
            TypeEntity p = (TypeEntity) o;
            if (p.getName().equals("null")) {
                tempTE = p;
                break;
            }

        }
        //System.out.println(tempTE.getId());

        ArrayList al = new ArrayList();

        Query q = em.createQuery("SELECT t from RoomEntity t");
        for (Object o : q.getResultList()) {
            RoomEntity p = (RoomEntity) o;
            System.out.println(p.getRoomNumber());
            if (p.getRoomNumber() == rm) {
                p.getType().getRooms().remove(p);
                p.setType(tempTE);
                em.merge(p.getType());
                em.merge(tempTE);
                em.merge(p);
                em.remove(p);
//                p.setType(tempTE);
//                tempTE.setRooms(al);
//                tempTE.getRooms().add(p);
//                em.merge(p);
//                em.merge(tempTE);
//                em.remove(p);
                System.out.println("removed room");
                break;
            }
        }
    }

    public void dummyType() {
        TypeEntity tempTE = new TypeEntity();
        tempTE.create("null", 0, 0);

        em.persist(tempTE);
    }

    public ArrayList viewBookingPayment() {
        ArrayList temp = new ArrayList();

        Query q = em.createQuery("SELECT t from BookingEntity t");
        for (Object o : q.getResultList()) {
            BookingEntity p = (BookingEntity) o;
            if (p.getAccount().getId() == userId) {
                temp.add(String.valueOf(p.getPayment().getAmountPaid()));
            }
            //System.out.println("viewbooking: " + temp.size());
        }
        return temp;
    }

    public ArrayList viewMyBookingPayment() {
        ArrayList temp = new ArrayList();

        Query q = em.createQuery("SELECT t from BookingEntity t");
        for (Object o : q.getResultList()) {
            BookingEntity p = (BookingEntity) o;
            temp.add(String.valueOf(p.getPayment().getAmountPaid()));
            //System.out.println("viewbooking: " + temp.size());
        }
        return temp;
    }

    public void updateBooking(String choice, String choiceId) {
        int first = Integer.parseInt(choice);
        long userId = Long.parseLong(choiceId);
        String status;
        if (first == 0) {
            status = "checked-in";

        } else {
            status = "checked-out";
        }

        Query q = em.createQuery("SELECT t from BookingEntity t");
        for (Object o : q.getResultList()) {
            BookingEntity p = (BookingEntity) o;
            if (p.getId() == userId) {
                p.setStatus(status);
                break;
            }
            //System.out.println("viewbooking: " + temp.size());
        }

    }

    public ArrayList viewBookingStatus() {
        ArrayList temp = new ArrayList();

        Query q = em.createQuery("SELECT t from BookingEntity t");
        for (Object o : q.getResultList()) {
            BookingEntity p = (BookingEntity) o;
            if (p.getAccount().getId() == userId) {
                temp.add(String.valueOf(p.getStatus()));
            }
            //System.out.println("viewbooking: " + temp.size());
        }
        return temp;
    }

    public ArrayList viewMyBookingStatus() {
        ArrayList temp = new ArrayList();

        Query q = em.createQuery("SELECT t from BookingEntity t");
        for (Object o : q.getResultList()) {
            BookingEntity p = (BookingEntity) o;
            temp.add(String.valueOf(p.getStatus()));
            System.out.println(String.valueOf(p.getStatus()));
        }
        return temp;
    }

    public ArrayList viewBookingCheckout() {
        ArrayList temp = new ArrayList();

        Query q = em.createQuery("SELECT t from BookingEntity t");
        for (Object o : q.getResultList()) {
            BookingEntity p = (BookingEntity) o;
            if (p.getAccount().getId() == userId) {
                temp.add(sdf.format(p.getCheckoutDate()));
                System.out.println("aaa"+sdf.format(p.getCheckoutDate()));
            }
            //System.out.println("viewbooking: " + temp.size());
        }
        return temp;
    }

    public ArrayList viewMyBookingCheckout() {
        ArrayList temp = new ArrayList();

        Query q = em.createQuery("SELECT t from BookingEntity t");
        for (Object o : q.getResultList()) {
            BookingEntity p = (BookingEntity) o;
            temp.add(sdf.format(p.getCheckoutDate()));
System.out.println("aaa"+sdf.format(p.getCheckoutDate()));
            //System.out.println("viewbooking: " + temp.size());
        }
        return temp;
    }

    public ArrayList viewBookingCheckin() {
        ArrayList temp = new ArrayList();

        Query q = em.createQuery("SELECT t from BookingEntity t");
        for (Object o : q.getResultList()) {
            BookingEntity p = (BookingEntity) o;
            if (p.getAccount().getId() == userId) {
                temp.add(sdf.format(p.getCheckinDate()));
                System.out.println("aa"+sdf.format(p.getCheckinDate()));
            }
            //System.out.println("viewbooking: " + temp.size());
        }
        return temp;
    }

    public ArrayList viewMyBookingCheckin() {
        ArrayList temp = new ArrayList();

        Query q = em.createQuery("SELECT t from BookingEntity t");
        for (Object o : q.getResultList()) {
            BookingEntity p = (BookingEntity) o;
            temp.add(sdf.format(p.getCheckinDate()));
            System.out.println("aa"+sdf.format(p.getCheckinDate()));
        }
        return temp;
    }

    public ArrayList viewBooking() {
        ArrayList temp = new ArrayList();

        Query q = em.createQuery("SELECT t from BookingEntity t");
        for (Object o : q.getResultList()) {
            BookingEntity p = (BookingEntity) o;
            System.out.println("viewbooking: reached 1" + userId);
            if (p.getAccount().getId() == userId) {
                System.out.println(p.getAccount());
                temp.add(String.valueOf(p.getId()));
            }
            System.out.println("viewbooking: " + temp.size());
        }
        return temp;
    }

    public ArrayList viewMyBooking() {
        ArrayList temp = new ArrayList();

        Query q = em.createQuery("SELECT t from BookingEntity t");
        for (Object o : q.getResultList()) {
            BookingEntity p = (BookingEntity) o;
            System.out.println("viewbooking: reached 1" + userId);
            System.out.println(p.getAccount());
            temp.add(String.valueOf(p.getId()));
            System.out.println("viewbooking: " + temp.size());
        }
        return temp;
    }

    public void makePayment(long id) {
        Query q = em.createQuery("SELECT t from BookingEntity t");
        for (Object o : q.getResultList()) {
            BookingEntity p = (BookingEntity) o;
            if (p.getId() == id) {
                p.setStatus("paid");
            }
        }
    }

    public ArrayList shoppingCartt() {

        ArrayList al = new ArrayList();
        Query n = em.createQuery("SELECT t from SHSMember t");
        System.out.println(username);
        for (Object o : n.getResultList()) {
            SHSMemberEntity s = (SHSMemberEntity) o;
            System.out.println("test: " + s.getPersonId());
            if (s.getPersonId().equals(null)) {

            } else if (s.getPersonId().equals(username)) {
                System.out.println("succeed");

                Collection<BookingEntity> temp2 = s.getBooking();
                System.out.println(temp2.size());
                Iterator itr = temp2.iterator();

                while (itr.hasNext()) {
                    BookingEntity element = (BookingEntity) itr.next();
                    if (element.getStatus().equals("unpaid")) {

                        Collection<RoomEntity> temp3 = element.getRooms();

                        Iterator itr2 = temp3.iterator();

                        while (itr2.hasNext()) {
                            RoomEntity element2 = (RoomEntity) itr2.next();
                            al.add(element2.getRoomNumber());
                        }

                    }
                    //System.out.println("display temp: " + temp + " " + element.getRoomNumber());
                }
                
            }
        }
        System.out.println(al);
        return al;
    }

    public long shoppingCart(ArrayList temp, Date checkin, Date checkout) {
        Query q = em.createQuery("SELECT t from RoomEntity t");
        double totalPrice = 0;
        Date today = new Date();

        System.out.println(username);

        BookingEntity booking = new BookingEntity();
        booking.create(today, checkin, checkout, "unpaid");
        ArrayList<RoomEntity> rooms = new ArrayList<RoomEntity>();
        ArrayList<BookingEntity> bookings = new ArrayList<BookingEntity>();
        bookings.add(booking);

        for (Object o : q.getResultList()) {
            RoomEntity p = (RoomEntity) o;
            for (int i = 0; i < temp.size(); i++) {
                if (String.valueOf(p.getRoomNumber()).equals(temp.get(i))) {
                    rooms.add(p);
                    totalPrice += p.getType().getPrice();
                    p.setBookings(bookings);
                }
            }
        }
        booking.setRooms(rooms);

        PaymentEntity pe = new PaymentEntity();
        pe.create(today, totalPrice);
        booking.setPayment(pe);
        em.persist(pe);

        Query n = em.createQuery("SELECT t from SHSMember t");
        for (Object o : n.getResultList()) {
            SHSMemberEntity s = (SHSMemberEntity) o;
            System.out.println("test: " + s.getPersonId());
            if (s.getPersonId().equals(null)) {

            } else if (s.getPersonId().equals(username)) {
                System.out.println("succeed");
                
                for (int j = 0; j< bookings.size(); j++) {
                    
                s.getBooking().add(bookings.get(j));
                }
                booking.setAccount(s);
                em.merge(booking);
                em.merge(s);
                System.out.println("succeed2");

                //to remove null member
                Query m = em.createQuery("SELECT t from SHSMember t");

                for (Object a : m.getResultList()) {
                    SHSMemberEntity pp = (SHSMemberEntity) a;
                    try {
                        if (pp.getName().equals(null)) {
                            em.remove(pp);
                        }
                    } catch (NullPointerException e) {
                        em.remove(pp);
                    }
                }

                break;
            }
        }

        return booking.getId();
    }

    public ArrayList searchRoomNumber(Date checkin, Date checkout) {
        ArrayList temp = new ArrayList();

        //find all room first
        Query q = em.createQuery("SELECT t from RoomEntity t");
        for (Object o : q.getResultList()) {
            RoomEntity p = (RoomEntity) o;
            temp.add(p.getRoomNumber());
        }

        //search booking and deduct the rooms from temp
        Query bk = em.createQuery("SELECT b from BookingEntity b");
        for (Object o : bk.getResultList()) {
            BookingEntity be = (BookingEntity) o;
            if ((checkin.compareTo(be.getCheckinDate()) >= 0 && checkin.compareTo(be.getCheckoutDate()) <= 0)
                    || (checkout.compareTo(be.getCheckinDate()) >= 0 && checkout.compareTo(be.getCheckoutDate()) <= 0)) {
                System.out.println("within range, must remove");
                Collection<RoomEntity> temp2 = be.getRooms();

                Iterator itr = temp2.iterator();

                while (itr.hasNext()) {
                    RoomEntity element = (RoomEntity) itr.next();
                    temp.remove(temp.indexOf(element.getRoomNumber()));
                    //System.out.println("display temp: " + temp + " " + element.getRoomNumber());
                }

            }

        }

        return temp;
    }

    public ArrayList searchRoomType(ArrayList data) {
        ArrayList temp = new ArrayList();
        
            for (int i = 0; i < data.size(); i++) {
        Query q = em.createQuery("SELECT t from RoomEntity t");
        for (Object o : q.getResultList()) {
            RoomEntity p = (RoomEntity) o;

                //System.out.println(data.get(0));
                if ((int)data.get(i)==p.getRoomNumber()) {
                    temp.add(p.getType().getName());
                   // System.out.println("added room");
                }
            }
        }
        return temp;
    }

    public ArrayList searchCapacity(ArrayList data) {
        ArrayList temp = new ArrayList();
        
            for (int i = 0; i < data.size(); i++) {
        Query q = em.createQuery("SELECT t from RoomEntity t");
        for (Object o : q.getResultList()) {
            RoomEntity p = (RoomEntity) o;


                if ((int)data.get(i)==p.getRoomNumber()) {
                    temp.add(p.getType().getMaxGuest());
                }
            }
        }
        return temp;
    }

    public ArrayList searchPrice(ArrayList data) {
        ArrayList temp = new ArrayList();
        
            for (int i = 0; i < data.size(); i++) {
        Query q = em.createQuery("SELECT t from RoomEntity t");
        for (Object o : q.getResultList()) {
            RoomEntity p = (RoomEntity) o;

                if ((int)data.get(i)==p.getRoomNumber()) {
                    temp.add(p.getType().getPrice());
                }
            }
        }
        return temp;
    }

    public boolean createMember(String passport, String name, String password, String postal, int phone, String email) {

        Query q = em.createQuery("SELECT t from SHSMember t");
        for (Object o : q.getResultList()) {
            SHSMemberEntity p = (SHSMemberEntity) o;
            if (p.getPersonId().equals(passport)) {
                return false;
            }
        }

        SHSMemberEntity c = new SHSMemberEntity();
        c.create(passport, name, password);
        ContactEntity contact = new ContactEntity();
        contact.create(postal, phone, email);
        c.setContact(contact);

        em.persist(contact);
        em.persist(c);
        return true;
    }

    public boolean getLogin() {
        return isLogin;
    }

    public void logout() {
        isLogin = false;
    }

    public boolean loginMember(String passport, String password) {

        Query q = em.createQuery("SELECT t from SHSMember t");
        for (Object o : q.getResultList()) {
            SHSMemberEntity p = (SHSMemberEntity) o;

            if (p.getPersonId().equals(passport)) {
                if (p.getPassword().equals(password)) {
                    isLogin = true;
                    username = p.getPersonId();
                    userId = p.getId();
                    return true;
                }
            }
        }
        isLogin = false;
        return false;
    }

    public long createRoomType(String nameType, int numOfGuest, double price) {
        typeEntity = new TypeEntity();
        typeEntity.create(nameType, numOfGuest, price);
        rooms = new ArrayList<RoomEntity>();
        return typeEntity.getId();
    }

    public void addRoom() {
        rooms.add(roomEntity);
    }

    public void persistRoomType() {
        em.persist(typeEntity);
    }

    public long createRoom(int roomNumber) {
        roomEntity = new RoomEntity();
        roomEntity.create(roomNumber);
        return roomEntity.getId();
    }

    public void persist() {
        System.out.println(typeEntity.getName());
        roomEntity.setType(typeEntity);
        em.persist(roomEntity);
        typeEntity.setRooms(rooms);
        em.persist(typeEntity);
    }

    public void associate(int roomNumber, String type) { //got error
        TypeEntity tempType = new TypeEntity();
        Query q = em.createQuery("SELECT t from TypeEntity t");
        for (Object o : q.getResultList()) {
            TypeEntity p = (TypeEntity) o;
            if (p.getName().equals(type)) {
                System.out.println("found type" + p.getName());
                tempType = p;
            }
        }

        RoomEntity re = new RoomEntity();
        re.create(roomNumber);
        re.setType(tempType);
        tempType.getRooms().add(re);

        em.merge(re);
        em.merge(tempType);

    }

    public void findRoomType(String roomType) {
        Query q = em.createQuery("SELECT t from TypeEntity t");
        for (Object o : q.getResultList()) {
            TypeEntity p = (TypeEntity) o;

            if (p.getName().equals(roomType)) {
                typeEntity = p;
            }
        }
    }

    public String deleteRoomType(String roomType) {
        Query q = em.createQuery("SELECT t from TypeEntity t");
        for (Object o : q.getResultList()) {
            TypeEntity p = (TypeEntity) o;

            if (p.getRooms().size() == 0) {

                if (p.getName().equals(roomType)) {
                    em.remove(p);
                    return "Successfully deleted " + roomType + " type from database";
                }
            }
        }
        return "Can't find the type or it is associated with with rooms";
    }

    @Remove
    public void remove() {
        System.out.println("PersonAddressManagerBean:remove()");
    }
}
