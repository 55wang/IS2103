package ejb;

import ejb.RoomEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-07-14T10:26:39")
@StaticMetamodel(ServiceEntity.class)
public class ServiceEntity_ { 

    public static volatile SingularAttribute<ServiceEntity, String> description;
    public static volatile SingularAttribute<ServiceEntity, Long> Id;
    public static volatile SingularAttribute<ServiceEntity, String> timeOfDay;
    public static volatile SingularAttribute<ServiceEntity, String> serviceType;
    public static volatile SingularAttribute<ServiceEntity, RoomEntity> rooms;

}