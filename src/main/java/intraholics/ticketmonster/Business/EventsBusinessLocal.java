/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intraholics.ticketmonster.Business;

import intraholics.ticketmonster.Entities.Events;
import intraholics.ticketmonster.SupplementaryClasses.CustomEvents;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Kostis
 */
@Local
public interface EventsBusinessLocal {
    public List<CustomEvents> findAllEvents();
    public CustomEvents findEventById(Integer eventID);
    public boolean addEvents(Events event);
    public boolean updateEvents(Events event);
    public boolean deleteEvents(Integer eventID);
}
