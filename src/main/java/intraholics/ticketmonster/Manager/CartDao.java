/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intraholics.ticketmonster.Manager;

import intraholics.ticketmonster.Entities.Cart;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Kostis
 */
@Stateless
public class CartDao implements CartDaoLocal{
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public Cart findCartByID(Integer ID) {
        return em.find(Cart.class, ID);
    }

    @Override
    public List<Cart> findCartByUser(Integer ID) {
        Query query=em.createQuery("SELECT c FROM Cart c WHERE c.userID=:userid",Cart.class);
        query.setParameter("userid",ID);
        return query.getResultList();
    }

    @Override
    public List<Cart> findAllCarts() {
        return em.createNamedQuery("Cart.findAll").getResultList();
    }

    @Override
    public boolean addCart(Cart cart) {
        em.persist(cart);
        return true;
    }

    @Override
    public boolean updateCart(Cart cart) {
        em.merge(cart);
        return true;
    }

    @Override
    public boolean deleteCartByID(Integer OrderID) {
        Cart found=em.find(Cart.class,OrderID);
        em.remove(found);
        return true;
    }
}
