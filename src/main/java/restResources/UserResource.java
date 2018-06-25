/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restResources;

import intraholics.ticketmonster.Entities.User;
import intraholics.ticketmonster.Manager.UserDaoLocal;
import intraholics.ticketmonster.validation.ValidationBean;
import intraholics.ticketmonster.validation.ValidationBeanLocal;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
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
import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author Kostis Hatzistamatis
 * Class for mapping the Rest endpoints For the user resources.
 * 
 */
@Path("/users")
@Stateless
public class UserResource {
    
    
    @EJB
    private UserDaoLocal user;
    @Inject
    private ValidationBeanLocal valid;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllUsers(){
        List<User> users=user.findAllUsers();
        return Response.ok(users).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response findUserById(@PathParam("id") Integer id){
        User found=user.findUserById(id);
        return Response.ok(found).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{Username}&{Pass}")
    public Response logged_user(@PathParam("Username") String username,@PathParam("Pass") String pass ){
     User found=user.checkLoginCredentials(username, pass);
     JsonObject usertolog=valid.addToValidated(found);
        return Response.ok(usertolog).build();
    }
    
        
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(User user1){ 
        user.addUser(user1);
        return Response.ok(user1).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(User user1){
        user.updateUser(user1);
        return Response.ok(user1).build();
    }
    
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id")Integer id){
        String newpass=PasswordGenerator();
        user.updateUser(id,newpass);
        return Response.ok().build();
    }

    @DELETE
    @Path ("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") Integer id){
        user.deleteUserById(id);
        return Response.ok().build();
    }
    
    
     public String PasswordGenerator()
    {
    	String s = "";
		Random random = new Random();
        for (int i = 0; i < 12; i++)
        {
            int randomNumber = random.nextInt(126) + 33;
            char c = (char)randomNumber;            
            s = s + Character.toString(c);            
        } 
        return s;
    }
}
