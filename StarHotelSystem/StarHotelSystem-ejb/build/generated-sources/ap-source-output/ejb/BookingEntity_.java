package ejb;

import ejb.PaymentEntity;
import ejb.RoomEntity;
import ejb.SHSMemberEntity;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-07-14T10:26:39")
@StaticMetamodel(BookingEntity.class)
public class BookingEntity_ { 

    public static volatile SingularAttribute<BookingEntity, Date> bookingDate;
    public static volatile SingularAttribute<BookingEntity, PaymentEntity> payment;
    public static volatile SingularAttribute<BookingEntity, String> status;
    public static volatile SingularAttribute<BookingEntity, SHSMemberEntity> account;
    public static volatile SingularAttribute<BookingEntity, Long> Id;
    public static volatile SingularAttribute<BookingEntity, Date> checkinDate;
    public static volatile SingularAttribute<BookingEntity, Date> checkoutDate;
    public static volatile CollectionAttribute<BookingEntity, RoomEntity> rooms;

}