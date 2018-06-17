/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restResources;

import intraholics.ticketmonster.Entities.Events;
import intraholics.ticketmonster.Manager.EventsDaoLocal;
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

/**
 * @author Kostis Hatzistamatis
 * Class for mapping the Rest endpoints For the Events resources.
 * 
 */

@Path("/events")
@Stateless
public class EventsResource {
    
    
    
    @EJB
    private EventsDaoLocal event;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllEvents(){
        List<Events> events=event.findAllEvents();
        return Response.ok(events).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response findEventById(@PathParam("id") Integer id){
        Events found=event.findEventbyId(id);
        return Response.ok(found).build();
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
