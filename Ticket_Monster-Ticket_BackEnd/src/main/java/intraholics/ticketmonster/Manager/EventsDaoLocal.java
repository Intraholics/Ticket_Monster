/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intraholics.ticketmonster.Manager;

import intraholics.ticketmonster.Entities.Events;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Kostis Hatzistamatis
 */
@Local
public interface EventsDaoLocal {
    public Events findEventbyId(Integer EventID);
    public List<Events> findAllEvents();
    public boolean addEvent(Events event);
    public boolean updateEvent(Events event);
    public boolean deleteEventById(Integer eventId);
}
