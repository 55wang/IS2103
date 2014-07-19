package ejb;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-07-14T10:26:39")
@StaticMetamodel(PaymentEntity.class)
public class PaymentEntity_ { 

    public static volatile SingularAttribute<PaymentEntity, Date> paid;
    public static volatile SingularAttribute<PaymentEntity, Double> amountPaid;
    public static volatile SingularAttribute<PaymentEntity, Long> Id;

}