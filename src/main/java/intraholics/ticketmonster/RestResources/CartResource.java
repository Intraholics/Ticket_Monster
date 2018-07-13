/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intraholics.ticketmonster.RestResources;

import intraholics.ticketmonster.Business.CartBusinessLocal;
import intraholics.ticketmonster.Entities.Cart;
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
import intraholics.ticketmonster.Manager.CartDaoLocal;
import intraholics.ticketmonster.security.ValidationBeanLocal;
import javax.inject.Inject;
import javax.ws.rs.HeaderParam;


/**
 * @author Kostantinos Hatzistamatis
 * Class for mapping the Rest endpoints For the cart resources.
 * 
 */

@Path("/cart")
@Stateless
public class CartResource {
    
    @Inject
    private CartBusinessLocal cart1;
    @Inject
    private ValidationBeanLocal valid;
    
    
    
    //*ENDPOINT to get all cart objects from the Database
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllCarts(@HeaderParam("Authorization") Integer Token){
        if(valid.checkIfValidated(Token)){
            return Response.ok(cart1.findAllCarts()).build();
        }
        else{
          return Response.status(Response.Status.UNAUTHORIZED).build();  
        }
    }
    
    
    //*ENDPOINT to get a cart object from the database
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response findCartById(@HeaderParam("Authorization") Integer Token,@PathParam("id") Integer id){
        if(valid.checkIfValidated(Token)){
            return Response.ok(cart1.findCartsById(id)).build();
        }
        else{
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
    
    //*ENDPOINT to get all Carts by userID
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user/{id}")
    public Response findCartByUser(@HeaderParam("Authorization") Integer Token,@PathParam("id") Integer userid){
        if(valid.checkIfValidated(Token)){
        return Response.ok(cart1.findCartsByUserId(userid)).build();
        }
        else{
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }//GenericEntity<List<JsonObject>> JsonEntity =new GenericEntity<List<JsonObject>>(Cartstosend){};
       
    
    
    //*ENDPOINT to add List of Cart objects to the Database
    @POST
    @Path("/carts")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCarts(@HeaderParam("Authorization") Integer Token,List<Cart> cart){
         if(valid.checkIfValidated(Token)){
             cart1.addCarts(cart);
             return Response.ok().build();
         }
         else{
             return Response.status(Response.Status.UNAUTHORIZED).build();
         }
    }
    
    
    
    
    //*ENDPOINT to add a new Cart object to the Database
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCart(@HeaderParam("Authorization") Integer Token,Cart cart){
         if(valid.checkIfValidated(Token)){
             cart1.addCart(cart);
             return Response.ok().build();
         }
         else{
             return Response.status(Response.Status.UNAUTHORIZED).build();
         }
    }
    
    //*ENDPOINT to update an existing Cart to the Database
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCart(@HeaderParam("Authorization") Integer Token,Cart cart){
         if(valid.checkIfValidated(Token)){
            cart1.updateCarts(cart);
            return Response.ok().build();
         }
         else{
           return Response.status(Response.Status.UNAUTHORIZED).build();  
         }
    }

    //*ENDPOINT to remove an existing Cart from the Database by it's id
    @DELETE
    @Path ("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCart(@HeaderParam("Authorization") Integer Token,@PathParam("id") Integer id){
         if(valid.checkIfValidated(Token)){
             cart1.deleteCarts(id);
             return Response.ok().build();
         }
         else{
            return Response.status(Response.Status.UNAUTHORIZED).build();
         }
    }
}
