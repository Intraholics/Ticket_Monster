/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restResources;

import intraholics.ticketmonster.Entities.Events;
import intraholics.ticketmonster.Manager.EventsDaoLocal;
import intraholics.ticketmonster.validation.ValidationBeanLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
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

@Path("/events")
@Stateless
public class EventsResource {
    
    
    
    @EJB
    private EventsDaoLocal event;
    @Inject
    private ValidationBeanLocal valid;
    
    
    //*ENDPOINT to get all event Objects from the Database
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response findAllEvents(@HeaderParam("Authorization") Integer Token){
        if(valid.checkIfValidated(Token)){
            List<Events> events=event.findAllEvents();
            List<JsonObject> Jsonevents = new ArrayList();
            for(int i=0; i<events.size(); i++){
                JsonObject newJO = Json.createObjectBuilder().add("eventID", events.get(i).getEventID())
                   .add("description",events.get(i).getDescription())
                   .add("date",events.get(i).getDate().toString().substring(0,16))
                   .add("ticketsLeft",events.get(i).getTicketsLeft())
                   .add("price", events.get(i).getPrice())
                   .build();          
           Jsonevents.add(newJO);
       }
       return Response.ok(Jsonevents).build();
        }
        else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
    
    //*ENDPOINT to get an existing event object from the database by it's id
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response findEventById(@HeaderParam("Authorization") Integer Token,@PathParam("id") Integer id){
        if(valid.checkIfValidated(Token)){
            Events found=event.findEventbyId(id);
            JsonObject newJO = Json.createObjectBuilder().add("eventID",found.getEventID())
                   .add("description",found.getDescription())
                   .add("date",found.getDate().toString())
                   .add("ticketsleft",found.getTicketsLeft())
                   .add("price",found.getPrice())
                   .build();
            return Response.ok(newJO).build();
        }
        else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
    
    //*ENDPOINT to add a new event object to the database
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEvent(@HeaderParam("Authorization") Integer Token,Events event1){ 
        if(valid.checkIfValidated(Token)){
            event.addEvent(event1);
            return Response.ok(event1).build();
        }
        else{
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
    //*ENDPOINT to update an existing event object to the database
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEvent(@HeaderParam("Authorization") Integer Token,Events event1){
        if(valid.checkIfValidated(Token)){
            event.updateEvent(event1);
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
            event.deleteEventById(id);
            return Response.ok().build();
        }
        else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
    
    

    
}
