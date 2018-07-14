/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restResources;

import intraholics.ticketmonster.Entities.User;
import intraholics.ticketmonster.Mail.EmailSessionBean;
import intraholics.ticketmonster.Manager.UserDaoLocal;
import intraholics.ticketmonster.security.Encryption;
import intraholics.ticketmonster.security.ValidationBeanLocal;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
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

@Path("/users")
@Stateless
public class UserResource {
    
    
    @EJB
    private UserDaoLocal user;
    @Inject
    private ValidationBeanLocal valid;
    @Inject 
    private EmailSessionBean mail;
    @Inject 
    private Encryption encryted;
    
    
    /*ENDPOINT to get all users on the database*/
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllUsers(@HeaderParam("Authorization") Integer Token){
        if(valid.checkIfValidated(Token)){
            List<User> users=user.findAllUsers();
            return Response.ok(users).build();
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
            User found=user.findUserById(id);
            return Response.ok(found).build();
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
     User found=user.checkLoginCredentials(username, pass);
     if(found!=null){
     JsonObject JsonUser=Json.createObjectBuilder()
             .add("userID",found.getUserID())
             .add("username",found.getUsername())
             .add("userRole",found.getUserRole()).build();
     Integer token=valid.addToValidated(found);
        return Response.ok(JsonUser).header("Access-Control-Expose-Headers","Authorization").header("Authorization", token).build();
     }
     else {
         return Response.ok().build();
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
    public Response addUser(@HeaderParam("Authorization") Integer Token,User user1){ 
            user1.setPassword(encryted.encryptMessage(user1.getPassword()));
            user.addUser(user1);
            return Response.ok(user1).build(); 
    }
    
    /*ENDPOINT to update an existing user's info on the database.
    All it's info are required for the update*/
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@HeaderParam("Authorization") Integer Token,User user1){
        if(valid.checkIfValidated(Token)){
            user.updateUser(user1);
            return Response.ok(user1).build();
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
            if(role==true){
               User usertoupdate=user.findUserById(id);
               boolean roletoggle=usertoupdate.getUserRole();
                   usertoupdate.setUserRole(!roletoggle);
                   user.updateUser(usertoupdate);
            }
            else {
            String newpass= PasswordGenerator();
            String npass =encryted.encryptMessage(newpass);
            String email = user.findUserById(id).getEmail().toString();
            user.updateUser(id,npass);
            mail.SendMail(email,newpass);
            }
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
            user.deleteUserById(id);
            return Response.ok().build();
        }
        else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
    
    /*Random String password generator*/
     public String PasswordGenerator()
    {
    	String s = "";
		for (int i = 0; i < 12; i++)
        {
        	Random rand = new Random();
        	int  n = rand.nextInt(9) + 0;
                                  
            s = s + n;            
        } 
        return s;
    }
     
}
