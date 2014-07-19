/*
 * PersonEntity.java
 */
package ejb;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class ServiceEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private long Id; 
    private String timeOfDay;
    private String serviceType;
    private String description;
    @ManyToOne(cascade={CascadeType.ALL})
    private RoomEntity rooms = new RoomEntity();
    
    
    public ServiceEntity() {
        setId(System.nanoTime());
    }
    
    public void create(String timeOfDay, String serviceType, String description) {
        this.setTime(timeOfDay);
        this.setServiceType(serviceType);
        this.setDescription(description);
    }

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public String getTime() {
        return timeOfDay;
    }

    public void setTime(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RoomEntity getRooms() {
        return rooms;
    }

    public void setRooms(RoomEntity rooms) {
        this.rooms = rooms;
    }

}
