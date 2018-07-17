package intraholics.ticketmonster.Entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Orders.class)
public abstract class Orders_ {

	public static volatile SingularAttribute<Orders, Date> purchaseDate;
	public static volatile SingularAttribute<Orders, Integer> orderID;
	public static volatile SingularAttribute<Orders, String> phone;
	public static volatile SingularAttribute<Orders, Cart> cartID;
	public static volatile SingularAttribute<Orders, String> creditcard;

}

