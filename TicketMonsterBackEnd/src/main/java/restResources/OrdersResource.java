/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restResources;

import intraholics.ticketmonster.Entities.Cart;
import intraholics.ticketmonster.Entities.Events;
import intraholics.ticketmonster.Entities.Orders;
import intraholics.ticketmonster.Manager.CartDaoLocal;
import intraholics.ticketmonster.Manager.EventsDaoLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import intraholics.ticketmonster.Manager.OrdersDaoLocal;
import intraholics.ticketmonster.security.ValidationBeanLocal;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.HeaderParam;

@Path("/orders")
@Stateless
public class OrdersResource {
    
    @EJB
    private OrdersDaoLocal order;
    @Inject
    private CartDaoLocal cart;
    @Inject
    private ValidationBeanLocal valid;
    @Inject 
    private EventsDaoLocal event;
   
    //*ENDPOINT to get all order objects from the database
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response findAllOrders(@HeaderParam("Authorization") Integer Token){
        if(valid.checkIfValidated(Token)){
            List <Orders> allorders=order.findAllOrders();
            List<JsonObject> Jsonorders= new ArrayList();
            if (allorders!=null){
                for(int i=0; i<allorders.size(); i++){
                    JsonObject Jsonorder=Json.createObjectBuilder()
                        .add("orderID",allorders.get(i).getOrderID())
                        .add("username",allorders.get(i).getCartID().getUserID().getUsername())
                        .add("purchaseDate",allorders.get(i).getPurchaseDate().toString().substring(0,16))
                        .add("quantity",allorders.get(i).getCartID().getQuantity())
                        .add("finalPrice",allorders.get(i).getCartID().getFinalPrice()).build();
                Jsonorders.add(Jsonorder);
                }
            return Response.ok(Jsonorders).build();
            }
            else {
                return Response.ok().build();
            }
        }
        else{
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }                 
    }
    
    //*ENDPOINT to get all order objects of a user from the database by it's id
    @GET
    @Path("/users/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response findOrderByUserId(@HeaderParam("Authorization") Integer Token,@PathParam("id") Integer id){
         if(valid.checkIfValidated(Token)){
             List<Orders> orders=order.findOrdersByUserId(id);
             List<JsonObject> Jsonorders = new ArrayList();
             if (orders!=null){
                 for(int i=0; i<orders.size(); i++){
                     JsonObject Jsonorder=Json.createObjectBuilder()
                        .add("orderID",orders.get(i).getOrderID())
                        .add("eventDescr",orders.get(i).getCartID().getEventID().getDescription())
                        .add("username",orders.get(i).getCartID().getUserID().getUsername())
                        .add("purchaseDate",orders.get(i).getPurchaseDate().toString().substring(0,16))
                        .add("quantity",orders.get(i).getCartID().getQuantity())
                        .add("finalPrice",orders.get(i).getCartID().getFinalPrice()).build();
                     Jsonorders.add(Jsonorder);
                 }
            return Response.ok(Jsonorders).build();
             }
             return Response.ok().build();
         }
         else{
             return Response.status(Response.Status.UNAUTHORIZED).build();
         }
    }
   
    //*ENDPOINT to get an order object from the database by it's id
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findOrderById(@HeaderParam("Authorization") Integer Token,@PathParam("id") Integer id){
       if(valid.checkIfValidated(Token)){
        Orders found=order.findOrderById(id);
        return Response.ok(found).build();
       }
       else{
           return Response.status(Response.Status.UNAUTHORIZED).build();
       }
    }
    //*ENDPOINT to add a new order object to the database
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes (MediaType.APPLICATION_JSON)
    public Response addOrder(@HeaderParam("Authorization") Integer Token,Orders order1){
        if(valid.checkIfValidated(Token)){
            Instant instant = LocalDateTime.now().toInstant(ZoneOffset.ofHours(3));
            Date date = Date.from(instant);
            order1.setPurchaseDate(date);
            order.addOrder(order1);
            Cart cartfound=cart.findCartByID(order1.getCartID().getCartID());
            cartfound.setCheckout(true);
            cart.updateCart(cartfound);
            return Response.ok(order1).build();
        }
        else{
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
    
    //*ENDPOINT to add a list of order objects to the database
    @POST
    @Path("/orders")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes (MediaType.APPLICATION_JSON)
    public Response addOrders(@HeaderParam("Authorization") Integer Token,List<Orders> order1){
         if(valid.checkIfValidated(Token)){
             for (int i=0; i<order1.size(); i++){
                 Instant instant = LocalDateTime.now().toInstant(ZoneOffset.ofHours(3));
                 Date date = Date.from(instant);
                 order1.get(i).setPurchaseDate(date);
                 order.addOrder(order1.get(i));
                 Cart cartfound=cart.findCartByID(order1.get(i).getCartID().getCartID());
                 cartfound.setCheckout(true);
                 cart.updateCart(cartfound);
             }
             return Response.ok(order1).build();
         }
         else {
              return Response.status(Response.Status.UNAUTHORIZED).build();
         }
    }
    
    
    //*ENDPOINT to update an existing order object to the database
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces (MediaType.APPLICATION_JSON)
    public Response updateOrder (@HeaderParam("Authorization") Integer Token,Orders order1){
        if(valid.checkIfValidated(Token)){
            order.updateOrder(order1);
            return Response.ok(order1).build();
        }
        else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
    
    //*ENDPOINT to remove an existing order object from the database by it's id
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces (MediaType.APPLICATION_JSON)
    public Response deleteOrderById (@HeaderParam("Authorization") Integer Token,@PathParam("id")Integer id){
        if(valid.checkIfValidated(Token)){
            Cart Cartplaced=order.findOrderById(id).getCartID();
            Events eventtoupdate=cart.findCartByID(Cartplaced.getCartID()).getEventID();
            eventtoupdate.setTicketsLeft(eventtoupdate.getTicketsLeft()+Cartplaced.getQuantity());
            event.updateEvent(eventtoupdate);
            order.deleteOrderById(id);
            return Response.ok().build();
        }
        else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
    
}
