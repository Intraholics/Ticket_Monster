/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intraholics.ticketmonster.Manager;

import intraholics.ticketmonster.Entities.Cart;
import java.util.List;
import javax.ejb.Local;

@Local
public interface CartDaoLocal {
    public Cart findCartByID(Integer ID);
    public List<Cart> findCartByUser(Integer ID);
    public List<Cart> findAllCarts();
    public boolean addCart(Cart cart);
    public boolean updateCart(Cart cart);
    public boolean deleteCartByID(Integer CartID);
}
