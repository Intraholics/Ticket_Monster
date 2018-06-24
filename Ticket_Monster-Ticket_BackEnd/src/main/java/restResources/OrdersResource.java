/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restResources;

import intraholics.ticketmonster.Entities.Orders;
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

/**
 * @author Kostis Hatzistamatis
 * Class for mapping the Rest endpoints For the Order resources.
 * 
 */

@Path("/orders")
@Stateless
public class OrdersResource {
    
    @EJB
    private OrdersDaoLocal order;
   
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllOrders(){
        List <Orders> allorders=order.findAllOrders();
        return Response.ok(allorders).build();
    }
   
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findOrderById(@PathParam("id") Integer id){
        Orders found=order.findOrderById(id);
        return Response.ok(found).build();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes (MediaType.APPLICATION_JSON)
    public Response addOrder(Orders order1){
        order.addOrder(order1);
        return Response.ok(order1).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces (MediaType.APPLICATION_JSON)
    public Response updateOrder (Orders order1){
        order.updateOrder(order1);
        return Response.ok(order1).build();
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces (MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deleteOrderById (@PathParam("id")Integer id){
        order.deleteOrderById(id);
        return Response.ok().build();
    }
    
}
