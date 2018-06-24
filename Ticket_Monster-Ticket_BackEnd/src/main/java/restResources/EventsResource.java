/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restResources;

import intraholics.ticketmonster.Entities.Events;
import intraholics.ticketmonster.Manager.EventsDaoLocal;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonValue;
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

@Path("/events")
@Stateless
public class EventsResource {
        
    @EJB
    private EventsDaoLocal event;
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response findAllEvents(){
    	List<Events> events=event.findAllEvents();
    	List<JsonObject> jsonList = new ArrayList();
        for(int i=0; i<events.size(); i++)
        {
        	JsonObject newJO = Json.createObjectBuilder().add("eventID", events.get(i).getEventID())
            		.add("description",events.get(i).getDescription())
            		.add("date",events.get(i).getDate().toString())
            		.add("ticketsLeft",events.get(i).getTicketsLeft())
            		.add("price", events.get(i).getPrice())
            		.build();      	
        	jsonList.add(newJO);
        }
        return Response.ok(jsonList).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response findEventById(@PathParam("id") Integer id){
        Events found=event.findEventbyId(id);
        JsonObject newJO = Json.createObjectBuilder().add("eventID", found.getEventID())
        		.add("description",found.getDescription())
        		.add("date",found.getDate().toString())
        		.add("ticketsLeft",found.getTicketsLeft())
        		.add("price", found.getPrice())
        		.build();
        return Response.ok(newJO).build();
        
    }
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEvent(Events event1){ 
        event.addEvent(event1);
        return Response.ok(event1).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEvent(Events event1){
        event.updateEvent(event1);
        return Response.ok(event1).build();
    }

    @DELETE
    @Path ("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEvent(@PathParam("id") Integer id){
        event.deleteEventById(id);
        return Response.ok().build();
    }
    
    

    
}
