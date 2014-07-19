/*
 * PersonEntity.java
 */
package ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class RoomEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private long Id; 
    private int roomNumber;
    @ManyToMany(cascade={CascadeType.ALL},mappedBy="rooms")
    private Collection<BookingEntity> bookings = new ArrayList<BookingEntity>();
    @ManyToOne(cascade={CascadeType.ALL})
    private TypeEntity types = new TypeEntity(); 
    
    public RoomEntity() {
        setId(System.nanoTime());
    }
    
    public Collection<BookingEntity> getBookings() {
        return bookings;
    }

    public void setBookings(Collection<BookingEntity> bookings) {
        this.bookings = bookings;
    }
    
    public void create(int roomNumber) {
        this.setRoomNumber(roomNumber);
    }

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public TypeEntity getType() {
        return types;
    }
    
    public void setType(TypeEntity types) {
        this.types = types;
    }
}
