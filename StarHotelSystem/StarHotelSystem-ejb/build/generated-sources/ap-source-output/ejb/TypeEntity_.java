package ejb;

import ejb.RoomEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-07-14T10:26:39")
@StaticMetamodel(TypeEntity.class)
public class TypeEntity_ { 

    public static volatile SingularAttribute<TypeEntity, Double> price;
    public static volatile SingularAttribute<TypeEntity, Integer> maxGuest;
    public static volatile SingularAttribute<TypeEntity, String> name;
    public static volatile SingularAttribute<TypeEntity, Long> Id;
    public static volatile CollectionAttribute<TypeEntity, RoomEntity> rooms;

}