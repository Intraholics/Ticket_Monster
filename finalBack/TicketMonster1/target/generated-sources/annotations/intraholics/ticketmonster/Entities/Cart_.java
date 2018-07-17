package intraholics.ticketmonster.Entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cart.class)
public abstract class Cart_ {

	public static volatile SingularAttribute<Cart, Events> eventID;
	public static volatile SingularAttribute<Cart, Integer> quantity;
	public static volatile SingularAttribute<Cart, Integer> cartID;
	public static volatile SingularAttribute<Cart, Integer> finalPrice;
	public static volatile CollectionAttribute<Cart, Orders> ordersCollection;
	public static volatile SingularAttribute<Cart, Boolean> checkout;
	public static volatile SingularAttribute<Cart, User> userID;

}

