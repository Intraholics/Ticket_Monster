/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intraholics.ticketmonster.Manager;

import intraholics.ticketmonster.Entities.Events;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EventsDao implements EventsDaoLocal{
    
    @PersistenceContext
    private EntityManager em;
    
    /*Finds an Event by it's Unique ID number*/
    @Override
    public Events findEventbyId(Integer EventID) {
        return em.find(Events.class, EventID);
    }

    /*Finds all Events stored on the Database*/
    @Override
    public List<Events> findAllEvents() {
        return em.createNamedQuery("Events.findAll").getResultList();
    }

    /*Adds a new Event object and stores it on the Database*/
    @Override
    public boolean addEvent(Events event) {
        java.text.SimpleDateFormat SQL_Datetime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = SQL_Datetime.format(LocalDateTime.now());
        String EventDatetime = SQL_Datetime.format( event.getDate());
        
        if (currentTime.compareTo(EventDatetime)<0) {
            return false;
        }
        else {
        em.persist(event);
        }
        return true;
    }

    /*Updates a new Event object and stores it on the Database*/
    @Override
    public boolean updateEvent(Events event) {
        em.merge(event);
        return true;
    }

    /*Removed an Event object from the Database*/
    @Override
    public boolean deleteEventById(Integer id) {
        Events found=em.find(Events.class, id);
        em.remove(found);
        return true;
    }
    
}
