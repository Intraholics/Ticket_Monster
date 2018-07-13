/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intraholics.ticketmonster.RestResources;

import intraholics.ticketmonster.Business.EventsBusiness;
import intraholics.ticketmonster.Business.EventsBusinessLocal;
import intraholics.ticketmonster.Entities.Events;
import intraholics.ticketmonster.security.ValidationBeanLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Kostis Hatzistamatis
 * Class for mapping the Rest endpoints For the Events resources.
 * 
 */

@Path("/events")
@Stateless
public class EventsResource {
    
    
    
    @Inject
    private EventsBusinessLocal event1;
    @Inject
    private ValidationBeanLocal valid;
    
    
    //*ENDPOINT to get all event Objects from the Database
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllEvents(@HeaderParam("Authorization") Integer Token){
        if(valid.checkIfValidated(Token)){
            return Response.ok(event1.findAllEvents()).build();
        }
        else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
      
    
    /*ENDPOINT to get an existing event object from the database by it's id*/
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response findEventById(@HeaderParam("Authorization") Integer Token,@PathParam("id") Integer id){
        if(valid.checkIfValidated(Token)){
            return Response.ok(event1.findEventById(id)).build();
        }
        else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
    
    //*ENDPOINT to add a new event object to the database
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEvent(@HeaderParam("Authorization") Integer Token,Events event){ 
        if(valid.checkIfValidated(Token)){
            event1.addEvents(event);
            return Response.ok().build();
        }
        else{
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
    
    //*ENDPOINT to update an existing event object to the database
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEvent(@HeaderParam("Authorization") Integer Token,Events event){
        if(valid.checkIfValidated(Token)){
            event1.updateEvents(event);
            return Response.ok(event1).build();
            }
        else{
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
    //*ENDPOINT to remove an existing event object from the database by it's id
    @DELETE
    @Path ("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEvent(@HeaderParam("Authorization") Integer Token,@PathParam("id") Integer id){
        if(valid.checkIfValidated(Token)){
            event1.deleteEvents(id);
            return Response.ok().build();
        }
        else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
