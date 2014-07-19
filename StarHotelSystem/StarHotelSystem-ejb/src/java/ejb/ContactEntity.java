/*
 * PersonEntity.java
 */
package ejb;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class ContactEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private long Id; 
    private String postalAddress;
    private int phoneNumber;
    private String emailAddress;
    
    public ContactEntity() {
        setId(System.nanoTime());
    }
    
    public void create(String postalAddress, int phoneNumber, String emailAddress) {
        this.setPostalAddress(postalAddress);
        this.setPhoneNumber(phoneNumber);
        this.setEmailAddress(emailAddress);
    }

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }


}
