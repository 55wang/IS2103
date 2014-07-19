/*
 * PersonEntity.java
 */
package ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class BookingEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private long Id;
    @Temporal(value = TemporalType.DATE)
    private Date bookingDate;
    @Temporal(value = TemporalType.DATE)
    private Date checkinDate;
    @Temporal(value = TemporalType.DATE)
    private Date checkoutDate;
    private String status;
    @ManyToOne(cascade = {CascadeType.ALL})
    private SHSMemberEntity account = new SHSMemberEntity();
    @OneToOne(cascade = {CascadeType.ALL})
    private PaymentEntity payment = new PaymentEntity();
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "BookingEntity_RoomEntity")
    private Collection<RoomEntity> rooms = new ArrayList<RoomEntity>();

    public BookingEntity() {
        setId(System.nanoTime());
    }

    public void create(Date bookingDate, Date checkinDate, Date checkoutDate, String status) {
        this.setBookingDate(bookingDate);
        this.setCheckinDate(checkinDate);
        this.setCheckoutDate(checkoutDate);
        this.setStatus(status);
    }

    public Collection<RoomEntity> getRooms() {
        return rooms;
    }

    public void setRooms(Collection<RoomEntity> rooms) {
        this.rooms = rooms;
    }

    public void setPayment(PaymentEntity payment) {
        this.payment = payment;
    }

    public PaymentEntity getPayment() {
        return payment;
    }

    public SHSMemberEntity getAccount() {
        return account;
    }

    public void setAccount(SHSMemberEntity account) {
        this.account = account;
    }

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Date getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
