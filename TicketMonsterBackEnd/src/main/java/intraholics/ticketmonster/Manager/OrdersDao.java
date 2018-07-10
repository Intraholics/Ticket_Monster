/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intraholics.ticketmonster.Manager;

import intraholics.ticketmonster.Entities.Cart;
import intraholics.ticketmonster.Entities.Events;
import intraholics.ticketmonster.Entities.Orders;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Dao Class For Implementing All the functionalities regarding with the Orders Entity
 */

@Stateless
public class OrdersDao implements OrdersDaoLocal{
    
    @PersistenceContext
    private EntityManager em;

    
    /*Finds an Order by it's Unique ID number*/
    @Override
    public Orders findOrderById (Integer ID) {
        return em.find(Orders.class, ID);
    }

    /*Finds all Order Objects stored on Database*/
    @Override
    public List<Orders> findAllOrders() {
        return em.createNamedQuery("Orders.findAll",Orders.class).getResultList();
    }

    /*Adds an Order Object to the Database*/
    @Override
    public boolean addOrder(Orders order) {
        Cart found=em.find(Cart.class, order.getCartID().getCartID());
        Events eventfound=em.find(Events.class,found.getEventID().getEventID());
        if ((eventfound.getTicketsLeft()-found.getQuantity())>= 0 ){
            eventfound.setTicketsLeft(eventfound.getTicketsLeft()-found.getQuantity());
            em.merge(found);
            em.persist(order);
            return true;  
        }
        else {
            return false;
        }
  
    }

    /*Updates an Order Object to the Database*/
    @Override
    public boolean updateOrder(Orders order) {
        em.merge(order);
        return true;
    }

    /*removes an Order Object from the Database*/
    @Override
    public boolean deleteOrderById(Integer orderID) {
        Orders todelete=em.find(Orders.class,orderID);
        em.remove(todelete);
        return true;
    }

    @Override
    public List<Orders> findOrdersByUserId(Integer Userid) {
        Query query=em.createQuery("SELECT o FROM Orders o WHERE o.cartID.userID.userID=:userid",Orders.class);
        query.setParameter("userid", Userid);
        return query.getResultList();
    }
    
}
