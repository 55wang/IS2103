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
import javax.persistence.OneToMany;


@Entity
public class TypeEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private long Id;
    private String name;
    private int maxGuest;
    private double price;
    @OneToMany(cascade={CascadeType.ALL})
    private Collection<RoomEntity> rooms = new ArrayList<RoomEntity>();

    
    public TypeEntity() {
        setId(System.nanoTime());
    }
    
    public void create(String name, int maxGuest, double price) {
        this.name=name;
        this.maxGuest=maxGuest;
        this.price=price;
    }

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxGuest() {
        return maxGuest;
    }

    public void setMaxGuest(int maxGuest) {
        this.maxGuest = maxGuest;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Collection<RoomEntity> getRooms() {
        return rooms;
    }

    public void setRooms(Collection<RoomEntity> rooms) {
        this.rooms = rooms;
    }


}
