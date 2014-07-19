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
import javax.persistence.OneToOne;

@Entity(name = "SHSMember")
public class SHSMemberEntity implements Serializable {

    @Id
    private long Id;
    private String personId; //passport
    private String name;
    private String password;
    @OneToOne(cascade = {CascadeType.ALL})
    private ContactEntity contact;
    @OneToMany(cascade = {CascadeType.ALL})
    private Collection<BookingEntity> booking = new ArrayList<BookingEntity>();

    public SHSMemberEntity() {
        setId(System.nanoTime());
    }

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public Collection<BookingEntity> getBooking() {
        return booking;
    }

    public void setBooking(Collection<BookingEntity> booking) {
        this.booking = booking;
    }

    public void create(String personId, String name, String password) {
        this.setPersonId(personId);
        this.setName(name);
        this.setPassword(password);
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ContactEntity getContact() {
        return contact;
    }

    public void setContact(ContactEntity contact) {
        this.contact = contact;
    }
}
