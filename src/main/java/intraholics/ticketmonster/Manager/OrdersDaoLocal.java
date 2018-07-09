/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intraholics.ticketmonster.Manager;

import intraholics.ticketmonster.Entities.Orders;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Kostis Hatzistamatis
 */
@Local
public interface OrdersDaoLocal {
    
    public Orders findOrderById(Integer OrderID);
    public List<Orders> findAllOrders();
    public boolean addOrder(Orders order);
    public boolean updateOrder(Orders order);
    public boolean deleteOrderById(Integer OrderId);
    public List<Orders> findOrdersByUserId(Integer Userid);
}
