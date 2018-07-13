/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intraholics.ticketmonster.RestResources;

import intraholics.ticketmonster.Entities.User;
import intraholics.ticketmonster.SupplementaryClasses.UserLogged;
import intraholics.ticketmonster.security.ValidationBeanLocal;
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
import javax.ws.rs.HeaderParam;
import intraholics.ticketmonster.Business.UsersBusinessLocal;
import javax.inject.Inject;

/**
 *
 * @author Kostis Hatzistamatis
 * Class for mapping the Rest endpoints For the user resources.
 * Token authentication required.If not found enpoints send null(empty) body response.
 * 
 */
@Path("/users")
@Stateless
public class UserResource {
    
    
    @Inject
    private UsersBusinessLocal user1;
    @Inject
    private ValidationBeanLocal valid;

    
    
    /*ENDPOINT to get all users on the database*/
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllUsers(@HeaderParam("Authorization") Integer Token){
        if(valid.checkIfValidated(Token)){
            return Response.ok(user1.findAllUsers()).build();
        }
        else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
    
    /*ENDPOINT to get the user with the given ID from the database*/
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response findUserById(@HeaderParam("Authorization") Integer Token,@PathParam("id") Integer id){
        if (valid.checkIfValidated(Token)){
            return Response.ok(user1.findUserById(id)).build();
        }
        else {
           return Response.status(Response.Status.UNAUTHORIZED).build(); 
        }
    }
    
    /*ENDPOINT for the users to log with their credentials.
    It searches on the database and if they are found
    a new token is produced and send back to the user with their userID,
    their username and their role(Admin?simpleUser)*/
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{Username}&{Pass}")
    public Response logged_user(@PathParam("Username") String username,@PathParam("Pass") String pass ){
     UserLogged found=user1.logUser(username, pass);
     if(found!=null){
         Integer token=valid.addToValidated(found);
        return Response.ok(found).header("Access-Control-Expose-Headers","Authorization").header("Authorization", token).build();
     }
     else {
         return Response.status(Response.Status.UNAUTHORIZED).build();
     }
    }
    
    
    /*ENDPOINT to logout the user from the application*/
    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response LogoutUser(@HeaderParam("Authorization") Integer Token,@PathParam("id") Integer id){ 
            if(valid.checkIfValidated(Token)){
                valid.removeFromValidated(Token);
            }
            return Response.ok().build(); 
    }


    
    /*ENDPOINT to add a new user to the database*/
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(@HeaderParam("Authorization") Integer Token,User user){
        user1.addUsers(user);
        return Response.ok().build(); 
    }
    
    /*ENDPOINT to update an existing user's info on the database.
    All it's info are required for the update*/
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@HeaderParam("Authorization") Integer Token,User user){
        if(valid.checkIfValidated(Token)){
            user1.updateUsers(user);
            return Response.ok().build();
        }
        else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
    
    /*ENDPOINT to update user's password to a new random string on the database
    The users id is required*/
    @PUT
    @Path("/{id}&{role}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@HeaderParam("Authorization") Integer Token,@PathParam("role") boolean role,@PathParam("id")Integer id) throws Exception{
        if (valid.checkIfValidated(Token)){
            user1.updateUsers(id,role);
            return Response.ok().build();
            }     
        else{
           return Response.status(Response.Status.UNAUTHORIZED).build(); 
        }
    }

    /*ENDPOINT to delete a user from the database given it's id*/
    @DELETE
    @Path ("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@HeaderParam("Authorization") Integer Token,@PathParam("id") Integer id){
        if(valid.checkIfValidated(Token)){
            user1.deleteUsers(id);
            return Response.ok().build();
        }
        else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
