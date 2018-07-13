/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intraholics.ticketmonster.Business;

import intraholics.ticketmonster.Entities.Orders;
import intraholics.ticketmonster.SupplementaryClasses.CustomAdminOrders;
import intraholics.ticketmonster.SupplementaryClasses.CustomOrders;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Kostis
 */
@Local
public interface OrdersBusinessLocal {
    public List<CustomAdminOrders> findAllOrders();
    public List<CustomOrders> findOrdersByUserId(Integer userID);
    public Orders findOrderById(Integer orderID);
    public boolean addOrder(Orders order);
    public boolean addOrders(List<Orders> order) throws Exception;
    public boolean updateOrders(Orders order);
    public boolean deleteOrdersById(Integer orderID)throws Exception; 
    
}
