/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intraholics.ticketmonster.Manager;

import intraholics.ticketmonster.Entities.Cart;
import intraholics.ticketmonster.Entities.Events;
import intraholics.ticketmonster.Entities.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Kostis Hatzistamatis
 * Dao Class For Implementing All the functionalities regarding with the Cart Entity
 * 
 */

@Stateless
public class CartDao implements CartDaoLocal{
    
    @PersistenceContext
    private EntityManager em;

    /*Finds a Cart by it's Unique ID number*/
    @Override
    public Cart findCartByID(Integer ID) {
        return em.find(Cart.class, ID);
    }

    /*Finds all Cart Objects stored on Database for a specific user by the Users Unique ID*/
    @Override
    public List<Cart> findCartByUser(Integer ID) {
        User found=em.find(User.class, ID);
        Query query=em.createQuery("SELECT c FROM Cart c WHERE c.userID=:userid",Cart.class);
        query.setParameter("userid",found);
        return query.getResultList();
    }

    /*Finds all Cart Objects stored on Database*/
    @Override
    public List<Cart> findAllCarts() {
        return em.createNamedQuery("Cart.findAll").getResultList();
    }

    /*Adds a new Cart Object and stores it on the Database*/
    @Override
    public boolean addCart(Cart cart) {
            em.persist(cart);
            return true;
    }

    
    /*Updates existing Cart Object stored on Database*/
    @Override
    public boolean updateCart(Cart cart) {
        em.merge(cart);
        return true;
    }

    @Override
    /*Removes existing Cart Object from the Database*/
    public boolean deleteCartByID(Integer OrderID) {
        Cart found=em.find(Cart.class,OrderID);
        em.remove(found);
        return true;
    }
}
