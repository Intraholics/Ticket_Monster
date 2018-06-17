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

/**
 *
 * @author Kostis
 */

@Path("/cart")
@Stateless
public class CartResource {
    
    @EJB
    private CartDaoLocal cart;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllCarts(){
        List<Cart> cartAll= cart.findAllCarts();
        return Response.ok(cartAll).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response findCartById(@PathParam("id") Integer id){
        Cart found=cart.findCartByID(id);
        return Response.ok(found).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user/{id}")
    public Response findCartByUser(@PathParam("id") Integer id){
        Cart found=cart.findCartByID(id);
        return Response.ok(found).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCart(Cart cart1){ 
        cart.addCart(cart1);
        return Response.ok(cart1).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCart(Cart cart1){
        cart.updateCart(cart1);
        return Response.ok(cart1).build();
    }

    @DELETE
    @Path ("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCart(@PathParam("id") Integer id){
        cart.deleteCartByID(id);
        return Response.ok("true").build();
    }
}
