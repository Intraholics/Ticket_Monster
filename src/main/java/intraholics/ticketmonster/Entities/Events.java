/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intraholics.ticketmonster.Entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Kostis
 */
@Entity
@Table(name = "events", catalog = "ticketmonster", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Events.findAll", query = "SELECT e FROM Events e")
    , @NamedQuery(name = "Events.findByEventID", query = "SELECT e FROM Events e WHERE e.eventID = :eventID")
    , @NamedQuery(name = "Events.findByDescription", query = "SELECT e FROM Events e WHERE e.description = :description")
    , @NamedQuery(name = "Events.findByDate", query = "SELECT e FROM Events e WHERE e.date = :date")
    , @NamedQuery(name = "Events.findByTicketsLeft", query = "SELECT e FROM Events e WHERE e.ticketsLeft = :ticketsLeft")
    , @NamedQuery(name = "Events.findByPrice", query = "SELECT e FROM Events e WHERE e.price = :price")})
public class Events implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "eventID", nullable = false)
    private Integer eventID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "description", nullable = false, length = 50)
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tickets_left", nullable = false)
    private int ticketsLeft;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price", nullable = false)
    private int price;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eventID")
    private Collection<Cart> cartCollection;

    public Events() {
    }

    public Events(Integer eventID) {
        this.eventID = eventID;
    }

    public Events(Integer eventID, String description, Date date, int ticketsLeft, int price) {
        this.eventID = eventID;
        this.description = description;
        this.date = date;
        this.ticketsLeft = ticketsLeft;
        this.price = price;
    }

    public Integer getEventID() {
        return eventID;
    }

    public void setEventID(Integer eventID) {
        this.eventID = eventID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTicketsLeft() {
        return ticketsLeft;
    }

    public void setTicketsLeft(int ticketsLeft) {
        this.ticketsLeft = ticketsLeft;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @XmlTransient
    public Collection<Cart> getCartCollection() {
        return cartCollection;
    }

    public void setCartCollection(Collection<Cart> cartCollection) {
        this.cartCollection = cartCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eventID != null ? eventID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Events)) {
            return false;
        }
        Events other = (Events) object;
        if ((this.eventID == null && other.eventID != null) || (this.eventID != null && !this.eventID.equals(other.eventID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "intraholics.ticketmonster.Entities.Events[ eventID=" + eventID + " ]";
    }
    
}
