/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intraholics.ticketmonster.Manager;

import intraholics.ticketmonster.Entities.Events;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Kostis
 */
@Stateless
public class EventsDao implements EventsDaoLocal{
    
    @PersistenceContext
    private EntityManager em;
    
    
    @Override
    public Events findEventbyId(Integer EventID) {
        return em.find(Events.class, EventID);
    }

    @Override
    public List<Events> findAllEvents() {
        return em.createNamedQuery("Events.findAll").getResultList();
    }

    @Override
    public boolean addEvent(Events event) {
        em.persist(event);
        return true;
    }

    @Override
    public boolean updateEvent(Events event) {
        em.merge(event);
        return true;
    }

    @Override
    public boolean deleteEventById(Integer id) {
        Events found=em.find(Events.class, id);
        em.remove(found);
        return true;
    }
    
}
