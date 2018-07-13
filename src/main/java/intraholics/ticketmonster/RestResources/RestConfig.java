/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor
 */
package intraholics.ticketmonster.RestResources;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Kostis Hatzistamatis
 * 
 * 
 */

/*------------------------>ENDPOINTS<---------------------------

-------------------------->USERS<----------------------------
http://localhost:8080/TicketMonster/api/users                        ->@GET->  Fetch all Users.
http://localhost:8080/TicketMonster/api/users/{id}                   ->@GET->  Fetch User with Database id={id}.
http://localhost:8080/TicketMonster/api/users/{username}&{password}  ->@GET->  Fetch User with Username={username} AND Password={password}.
http://localhost:8080/TicketMonster/api/users                        ->@POST-> Add New User
http://localhost:8080/TicketMonster/api/users/{id}                   ->@POST-> User Logout
http://localhost:8080/TicketMonster/api/users                        ->@PUT->  Update User
http://localhost:8080/TicketMonster/api/users/{id}                   ->@PUT->  Update User by setting it's password to random String or making him admin
http://localhost:8080/TicketMonster/api/users/{id}                   ->@DELETE-> Delete User with id={id}
------------------------->USERS<-----------------------------

------------------------->ORDERS<--------------------------
http://localhost:8080/TicketMonster/api/orders                      ->@GET->  Fetch all Orders.
http://localhost:8080/TicketMonster/api/orders/{id}                 ->@GET->  Fetch Order with id={id}.
http://localhost:8080/TicketMonster/api/orders/users/{id}           ->@GET->  Fetch Orders with Userid={id}.
http://localhost:8080/TicketMonster/api/orders/                     ->@POST-> Add new Order.
http://localhost:8080/TicketMonster/api/orders/orders               ->@POST-> Add list of Orders.
http://localhost:8080/TicketMonster/api/orders/                     ->@PUT->  Update Order.
http://localhost:8080/TicketMonster/api/orders/{id}                 ->@DELETE-> Delete Order with id={id}.
------------------------->ORDERS<--------------------------

------------------------->EVENTS<--------------------------
http://localhost:8080/TicketMonster/api/events                      ->@GET->  Fetch all Events.
http://localhost:8080/TicketMonster/api/events/{id}                 ->@GET->  Fetch Event with id={id}.
http://localhost:8080/TicketMonster/api/events/                     ->@POST-> Add new Event.
http://localhost:8080/TicketMonster/api/events/                     ->@PUT->  Update Event.
http://localhost:8080/TicketMonster/api/events/{id}                 ->@DELETE-> Delete Event with id={id}.
------------------------->EVENTS<--------------------------

------------------------->CART<--------------------------
http://localhost:8080/TicketMonster/api/cart                      ->@GET->  Fetch all Carts.
http://localhost:8080/TicketMonster/api/cart/{id}                 ->@GET->  Fetch Cart with id={id}.
http://localhost:8080/TicketMonster/api/cart/user/{id}            ->@GET->  Fetch All Carts with UserId={id}.
http://localhost:8080/TicketMonster/api/cart/                     ->@POST-> Add new Cart.
http://localhost:8080/TicketMonster/api/cart/carts                ->@POST-> Add List of Carts.
http://localhost:8080/TicketMonster/api/cart/                     ->@PUT->  Update Cart.
http://localhost:8080/TicketMonster/api/cart/{id}                 ->@DELETE-> Delete Cart with id={id}.
------------------------->CART<--------------------------

*/





@ApplicationPath("api")
public class RestConfig extends Application{
    

@Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        // in order to enable a resource, the resource must be explicitly added to the 'classes' set
        classes.add(UserResource.class);
        classes.add(CartResource.class);
        classes.add(OrdersResource.class);
        classes.add(EventsResource.class);
        return classes;
    }
}