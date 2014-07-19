package ejb;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-07-14T10:26:39")
@StaticMetamodel(ContactEntity.class)
public class ContactEntity_ { 

    public static volatile SingularAttribute<ContactEntity, Integer> phoneNumber;
    public static volatile SingularAttribute<ContactEntity, String> postalAddress;
    public static volatile SingularAttribute<ContactEntity, String> emailAddress;
    public static volatile SingularAttribute<ContactEntity, Long> Id;

}