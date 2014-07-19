package ejb;

import ejb.BookingEntity;
import ejb.TypeEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-07-14T10:26:39")
@StaticMetamodel(RoomEntity.class)
public class RoomEntity_ { 

    public static volatile SingularAttribute<RoomEntity, Integer> roomNumber;
    public static volatile CollectionAttribute<RoomEntity, BookingEntity> bookings;
    public static volatile SingularAttribute<RoomEntity, Long> Id;
    public static volatile SingularAttribute<RoomEntity, TypeEntity> types;

}