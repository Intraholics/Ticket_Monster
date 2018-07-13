/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intraholics.ticketmonster.SupplementaryClasses;

import javax.ejb.Stateless;

/**
 *
 * @author Kostis
 */

public class CustomOrders {
    
    private Integer orderID;
    private String eventDescr;
    private String username;
    private String purchaseDate;
    private Integer quantity;
    private Integer finalPrice;

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public String getEventDescr() {
        return eventDescr;
    }

    public void setEventDescr(String eventDescr) {
        this.eventDescr = eventDescr;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Integer finalPrice) {
        this.finalPrice = finalPrice;
    }

}
