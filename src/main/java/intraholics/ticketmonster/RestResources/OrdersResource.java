/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intraholics.ticketmonster.RestResources;

import intraholics.ticketmonster.Business.OrdersBusinessLocal;
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
import intraholics.ticketmonster.security.ValidationBeanLocal;
import javax.inject.Inject;
import javax.ws.rs.HeaderParam;

/**
 * @author Kostis Hatzistamatis
 * Class for mapping the Rest endpoints For the Order resources.
 * 
 */

@Path("/orders")
@Stateless
public class OrdersResource {
    
    @Inject
    private OrdersBusinessLocal order1;
    @Inject
    private ValidationBeanLocal valid;
   
    //*ENDPOINT to get all order objects from the database
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllOrders(@HeaderParam("Authorization") Integer Token){
        if(valid.checkIfValidated(Token)){
            return Response.ok(order1.findAllOrders()).build();
            }
        else{
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }                 
    }
    
    //*ENDPOINT to get all order objects of a user from the database by it's id
    @GET
    @Path("/users/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findOrderByUserId(@HeaderParam("Authorization") Integer Token,@PathParam("id") Integer id){
         if(valid.checkIfValidated(Token)){
            return Response.ok(order1.findOrdersByUserId(id)).build();
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
        return Response.ok(order1.findOrderById(id)).build();
       }
       else{
           return Response.status(Response.Status.UNAUTHORIZED).build();
       }
    }
    //*ENDPOINT to add a new order object to the database
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes (MediaType.APPLICATION_JSON)
    public Response addOrder(@HeaderParam("Authorization") Integer Token,Orders order){
        if(valid.checkIfValidated(Token)){
            order1.addOrder(order);
            return Response.ok().build();
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
    public Response addOrders(@HeaderParam("Authorization") Integer Token,List<Orders> order) throws Exception{
         if(valid.checkIfValidated(Token)){
             order1.addOrders(order);
             return Response.ok().build();
         }
         else {
              return Response.status(Response.Status.UNAUTHORIZED).build();
         }
    }
    
    
    //*ENDPOINT to update an existing order object to the database
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces (MediaType.APPLICATION_JSON)
    public Response updateOrder (@HeaderParam("Authorization") Integer Token,Orders order){
        if(valid.checkIfValidated(Token)){
            order1.updateOrders(order);
            return Response.ok().build();
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
    public Response deleteOrderById (@HeaderParam("Authorization") Integer Token,@PathParam("id")Integer id) throws Exception{
        if(valid.checkIfValidated(Token)){
            order1.deleteOrdersById(id);
            return Response.ok().build();
        }
        else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
    
}
