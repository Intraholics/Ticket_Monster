package intraholics.ticketmonster.Entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Events.class)
public abstract class Events_ {

	public static volatile SingularAttribute<Events, Date> date;
	public static volatile CollectionAttribute<Events, Cart> cartCollection;
	public static volatile SingularAttribute<Events, Integer> eventID;
	public static volatile SingularAttribute<Events, Integer> price;
	public static volatile SingularAttribute<Events, String> description;
	public static volatile SingularAttribute<Events, Integer> ticketsLeft;

}

