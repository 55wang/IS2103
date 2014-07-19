/*
 * PersonEntity.java
 */
package ejb;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class PaymentEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private long Id; 
    @Temporal(value = TemporalType.DATE)
    private Date paid;
    private double amountPaid;
    
    
    public PaymentEntity() {
        setId(System.nanoTime());
    }
    
    public void create(Date paid, double amountPaid) {
        this.setPaid(paid);
        this.setAmountPaid(amountPaid);
    }

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public Date getPaid() {
        return paid;
    }

    public void setPaid(Date paid) {
        this.paid = paid;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }


}
