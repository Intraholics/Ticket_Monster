/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restResources;

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
import java.util.ArrayList;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.core.GenericEntity;


/**
 * @author Kostantinos Hatzistamatis
 * Class for mapping the Rest endpoints For the cart resources.
 * 
 */

@Path("/cart")
@Stateless
public class CartResource {
    
    @EJB
    private CartDaoLocal cart;
    @Inject
    private ValidationBeanLocal valid;
    
    
    
    //*ENDPOINT to get all cart objects from the Database
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllCarts(@HeaderParam("Authorization") Integer Token){
        if(valid.checkIfValidated(Token)){
            List<Cart> cartAll= cart.findAllCarts();
            return Response.ok(cartAll).build();
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
            Cart found=cart.findCartByID(id);
            return Response.ok(found).build();
        }
        else{
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
    
    //*ENDPOINT to get all Carts by userID
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/user/{id}")
    public Response findCartByUser(@HeaderParam("Authorization") Integer Token,@PathParam("id") Integer userid){
        if(valid.checkIfValidated(Token)){
            List<Cart> found=cart.findCartByUser(userid);
            List<JsonObject> Cartstosend=new ArrayList();
            for (int i=0; i<found.size(); i++){
                if(found.get(i).getCheckout()==false){
                    JsonObject tosend=Json.createObjectBuilder()
                        .add("cartID",found.get(i).getCartID())
                        .add("eventDescr",found.get(i).getEventID().getDescription())
                        .add("username",found.get(i).getUserID().getUsername())
                        .add("quantity",found.get(i).getQuantity())
                        .add("finalPrice",found.get(i).getFinalPrice()).build();
                    Cartstosend.add(tosend);
                }
            }
            return Response.ok(Cartstosend).build();
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
    public Response addCarts(@HeaderParam("Authorization") Integer Token,List<Cart> cart1){
         if(valid.checkIfValidated(Token)){
             Integer cartsposted=0;
             for (int i=0; i<cart1.size(); i++){
                 cart.addCart(cart1.get(i));
                 cartsposted++;
             }
             return Response.ok(cartsposted).build();
         }
         else{
             return Response.status(Response.Status.UNAUTHORIZED).build();
         }
    }
    
    
    
    
    //*ENDPOINT to add a new Cart object to the Database
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCart(@HeaderParam("Authorization") Integer Token,Cart cart1){
         if(valid.checkIfValidated(Token)){
             cart.addCart(cart1);
             return Response.ok(cart1).build();
         }
         else{
             return Response.status(Response.Status.UNAUTHORIZED).build();
         }
    }
    
    //*ENDPOINT to update an existing Cart to the Database
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCart(@HeaderParam("Authorization") Integer Token,Cart cart1){
         if(valid.checkIfValidated(Token)){
             cart.updateCart(cart1);
             return Response.ok(cart1).build();
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
             cart.deleteCartByID(id);
             return Response.ok().build();
         }
         else{
            return Response.status(Response.Status.UNAUTHORIZED).build();
         }
    }
}
