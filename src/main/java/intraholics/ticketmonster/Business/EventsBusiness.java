/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intraholics.ticketmonster.Business;

import intraholics.ticketmonster.Entities.Events;
import intraholics.ticketmonster.Manager.EventsDaoLocal;
import intraholics.ticketmonster.SupplementaryClasses.CustomEvents;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Kostis
 */


@Stateless
public class EventsBusiness implements EventsBusinessLocal {
    
   @Inject
   private EventsDaoLocal event1;
   
   

    @Override
    public List<CustomEvents> findAllEvents() {
        List<Events> eventlist=event1.findAllEvents();
        List<CustomEvents> customevents= new ArrayList();
        for(int i=0; i<eventlist.size(); i++){
            CustomEvents customevent=new CustomEvents();
            customevent.setEventID(eventlist.get(i).getEventID());
            customevent.setDate(eventlist.get(i).getDate().toString().substring(0,16));
            customevent.setDescription(eventlist.get(i).getDescription());
            customevent.setPrice(eventlist.get(i).getPrice());
            customevent.setTicketsLeft(eventlist.get(i).getTicketsLeft());
            customevents.add(customevent);
        }
        return customevents;
    }

    @Override
    public CustomEvents findEventById(Integer eventID) {
        Events event=event1.findEventbyId(eventID);
        CustomEvents customevent=new CustomEvents();
            customevent.setEventID(event.getEventID());
            customevent.setDate(event.getDate().toString().substring(0,16));
            customevent.setDescription(event.getDescription());
            customevent.setPrice(event.getPrice());
            customevent.setTicketsLeft(event.getTicketsLeft());
        return customevent;
    }

    @Override
    public boolean addEvents(Events event) {
        event1.addEvent(event);
        return true;
    }

    @Override
    public boolean updateEvents(Events event) {
        event1.updateEvent(event);
        return true;
    }

    @Override
    public boolean deleteEvents(Integer eventID) {
        event1.deleteEventById(eventID);
        return true;
    }

    
}
