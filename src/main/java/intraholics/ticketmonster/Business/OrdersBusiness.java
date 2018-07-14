/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intraholics.ticketmonster.Business;

import intraholics.ticketmonster.Entities.Cart;
import intraholics.ticketmonster.Entities.Events;
import intraholics.ticketmonster.Entities.Orders;
import intraholics.ticketmonster.Mail.EmailSessionBean;
import intraholics.ticketmonster.Manager.CartDaoLocal;
import intraholics.ticketmonster.Manager.EventsDaoLocal;
import intraholics.ticketmonster.Manager.OrdersDaoLocal;
import intraholics.ticketmonster.SupplementaryClasses.CustomAdminOrders;
import intraholics.ticketmonster.SupplementaryClasses.CustomOrders;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Kostis
 */
@Stateless
public class OrdersBusiness implements OrdersBusinessLocal {
    
    @Inject
    private OrdersDaoLocal order1;
    @Inject
    private CartDaoLocal cart1;
    @Inject
    private EventsDaoLocal event1;
    @Inject
    private EmailSessionBean mail;

    @Override
    public List<CustomAdminOrders> findAllOrders() {
        List<Orders> orders=order1.findAllOrders();
        List<CustomAdminOrders> adminorders=new ArrayList();
        for(int i=0; i<orders.size(); i++){
            CustomAdminOrders adminorder= new CustomAdminOrders();
            adminorder.setOrderID(orders.get(i).getOrderID());
            adminorder.setPurchaseDate(orders.get(i).getPurchaseDate().toString().substring(0,16));
            adminorder.setFinalPrice(orders.get(i).getCartID().getFinalPrice());
            adminorder.setQuantity(orders.get(i).getCartID().getQuantity());
            adminorder.setUsername(orders.get(i).getCartID().getUserID().getUsername());
            adminorders.add(adminorder);
        }
        return adminorders;
    }

    @Override
    public List<CustomOrders> findOrdersByUserId(Integer userID) {
        List<Orders> userorders=order1.findOrdersByUserId(userID);
        List<CustomOrders> usercustomorders = new ArrayList();
        for(int i=0; i<userorders.size(); i++){
            CustomOrders usercustomorder=new CustomOrders();
            usercustomorder.setOrderID(userorders.get(i).getOrderID());
            usercustomorder.setEventDescr(userorders.get(i).getCartID().getEventID().getDescription());
            usercustomorder.setQuantity(userorders.get(i).getCartID().getQuantity());
            usercustomorder.setFinalPrice(userorders.get(i).getCartID().getFinalPrice());
            usercustomorder.setUsername(userorders.get(i).getCartID().getUserID().getUsername());
            usercustomorder.setPurchaseDate(userorders.get(i).getPurchaseDate().toString().substring(0, 16));
            usercustomorders.add(usercustomorder);
        }
        return usercustomorders;
    }

    @Override
    public Orders findOrderById(Integer orderID) {
        return order1.findOrderById(orderID);
    }

    @Override
    public boolean addOrder(Orders order) {
        Cart cartfound=cart1.findCartByID(order.getCartID().getCartID());
        Events eventfound=event1.findEventbyId(cartfound.getEventID().getEventID());
        if (cartfound.getQuantity()<eventfound.getTicketsLeft()){
            Instant instant = LocalDateTime.now().toInstant(ZoneOffset.ofHours(3));
            Date date = Date.from(instant);
            order.setPurchaseDate(date);
            cartfound.setCheckout(true);
            cart1.updateCart(cartfound);
            eventfound.setTicketsLeft(eventfound.getTicketsLeft()-cartfound.getQuantity());
            event1.updateEvent(eventfound);
            order1.addOrder(order);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean addOrders(List<Orders> order) throws Exception {
        Integer Price =0;
        String creditcard=order.get(0).getCreditcard();
        String email=cart1.findCartByID(order.get(0).getCartID().getCartID()).getUserID().getEmail();
             for (int i=0; i<order.size(); i++){
                    Cart cartfound=cart1.findCartByID(order.get(i).getCartID().getCartID());
                    Events eventfound=event1.findEventbyId(cartfound.getEventID().getEventID());
                 if (cartfound.getQuantity()<eventfound.getTicketsLeft()) {
                 Instant instant = LocalDateTime.now().toInstant(ZoneOffset.ofHours(3));
                 Date date = Date.from(instant);
                 order.get(i).setPurchaseDate(date);
                 order1.addOrder(order.get(i));
                 cartfound.setCheckout(true);
                 cart1.updateCart(cartfound);
                
                 eventfound.setTicketsLeft(eventfound.getTicketsLeft()-cartfound.getQuantity());
                 event1.updateEvent(eventfound);
                 
                 Price=Price + cart1.findCartByID(order.get(i).getCartID().getCartID()).getFinalPrice();
                 }
            }
//      mail.SendReceipt(email,Price.toString(), creditcard);
        return true;
        }



    @Override
    public boolean updateOrders(Orders order) {
        Integer TicketLeft=cart1.findCartByID(order.getCartID().getCartID()).getEventID().getTicketsLeft();
        if (order.getCartID().getQuantity()<TicketLeft){
            order1.updateOrder(order);
            return true;
        }
        else{
            return false;
        }
        
    }

    @Override
    public boolean deleteOrdersById(Integer orderID) throws Exception{
        Orders orderfound = order1.findOrderById(orderID);
        String email=orderfound.getCartID().getUserID().getEmail();
        Integer sum=orderfound.getCartID().getFinalPrice();
        Integer TicketsReserved=cart1.findCartByID(order1.findOrderById(orderID).getCartID().getCartID()).getQuantity();
        Events event=event1.findEventbyId(order1.findOrderById(orderID).getCartID().getEventID().getEventID());
        event.setTicketsLeft(event.getTicketsLeft()+TicketsReserved);
        event1.updateEvent(event);
        Cart toupdate=cart1.findCartByID(order1.findOrderById(orderID).getCartID().getCartID());
        toupdate.setCheckout(true);
        cart1.updateCart(toupdate);        
        order1.deleteOrderById(orderID);

//        mail.SendRefund(email, sum.toString(),OrderID.toString());
        return true;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
