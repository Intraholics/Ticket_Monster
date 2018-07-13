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

public class UserLogged {
    
    private Integer UserID;
    private String Username;
    private boolean UserRole;

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer UserID) {
        this.UserID = UserID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public boolean isUserRole() {
        return UserRole;
    }

    public void setUserRole(boolean UserRole) {
        this.UserRole = UserRole;
    }
}
