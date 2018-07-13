/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intraholics.ticketmonster.Business;

import intraholics.ticketmonster.Entities.Cart;
import intraholics.ticketmonster.Manager.CartDaoLocal;
import intraholics.ticketmonster.SupplementaryClasses.CustomCart;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Kostis
 */

@Stateless
public class CartBusiness implements CartBusinessLocal {
    
    @Inject
    private CartDaoLocal cart1;

    @Override
    public List<Cart> findAllCarts() {
        return cart1.findAllCarts();
    }

    @Override
    public Cart findCartsById(Integer cartID) {
        return cart1.findCartByID(cartID);
    }

    @Override
    public List<CustomCart> findCartsByUserId(Integer userID) {
        List<Cart> carts = cart1.findCartByUser(userID);
        List<CustomCart> usercarts = new ArrayList();
        for (int i=0; i<carts.size(); i++){
            CustomCart usercart=new CustomCart();
            usercart.setCartID(carts.get(i).getCartID());
            usercart.setEventDescr(carts.get(i).getEventID().getDescription());
            usercart.setFinalPrice(carts.get(i).getFinalPrice());
            usercart.setQuantity(carts.get(i).getQuantity());
            usercart.setUsername(carts.get(i).getUserID().getUsername());
            usercarts.add(usercart);
        }
        return usercarts;
    }

    @Override
    public boolean addCart(Cart cart) {
        cart1.addCart(cart);
        return true;
    }

    @Override
    public boolean addCarts(List<Cart> cart) {
        for (int i=0; i<cart.size(); i++){
                 this.addCart(cart.get(i));
        }
    return true;
    }

    @Override
    public boolean updateCarts(Cart cart) {
        cart1.updateCart(cart);
        return true;
    }

    @Override
    public boolean deleteCarts(Integer cartID) {
        cart1.deleteCartByID(cartID);
        return true;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
