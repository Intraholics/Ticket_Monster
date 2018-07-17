/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intraholics.ticketmonster.validation;

import intraholics.ticketmonster.Entities.User;
import java.util.List;
import javax.ejb.Singleton;
import java.util.ArrayList;

@Singleton
public class ValidationBean implements ValidationBeanLocal{
    
    
    
    List<Integer> users_logged=new ArrayList();

    
    @Override
    public Integer addToValidated(User UserFound){    
        if (UserFound==null){
            return null;
        }
        else {
           Integer token=UserFound.getUsername().hashCode();
            if (!this.checkIfValidated(token)){
                users_logged.add(token);
            }
            return token;
        }
    }
    @Override
    public boolean checkIfValidated(Integer token) {
        return users_logged.contains(token);
    }

    @Override
    public boolean removeFromValidated(Integer token) {
        if (this.checkIfValidated(token)){
            users_logged.remove(token);
            return true;
        }
        else {
            return false;
        }
    }
}
