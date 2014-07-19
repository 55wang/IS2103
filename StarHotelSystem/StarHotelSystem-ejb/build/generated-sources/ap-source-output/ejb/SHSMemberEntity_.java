package ejb;

import ejb.BookingEntity;
import ejb.ContactEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-07-14T10:26:39")
@StaticMetamodel(SHSMemberEntity.class)
public class SHSMemberEntity_ { 

    public static volatile CollectionAttribute<SHSMemberEntity, BookingEntity> booking;
    public static volatile SingularAttribute<SHSMemberEntity, String> name;
    public static volatile SingularAttribute<SHSMemberEntity, String> personId;
    public static volatile SingularAttribute<SHSMemberEntity, Long> Id;
    public static volatile SingularAttribute<SHSMemberEntity, String> password;
    public static volatile SingularAttribute<SHSMemberEntity, ContactEntity> contact;

}