/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intraholics.ticketmonster.Business;

import intraholics.ticketmonster.Entities.Cart;
import intraholics.ticketmonster.SupplementaryClasses.CustomCart;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Kostis
 */
@Local
public interface CartBusinessLocal {
    public List<Cart> findAllCarts();
    public Cart findCartsById(Integer cartID);
    public List<CustomCart> findCartsByUserId(Integer userID);
    public boolean addCart(Cart cart);
    public boolean addCarts(List<Cart> cart);
    public boolean updateCarts(Cart cart);
    public boolean deleteCarts(Integer cartID);
}
