/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intraholics.ticketmonster.validation;

import intraholics.ticketmonster.Entities.User;
import java.util.List;
import javax.ejb.Singleton;
import javax.json.JsonObject;
import java.util.ArrayList;
import javax.json.Json;



/**
 *
 * @author Konstantinos Hatzistamatis
 */

@Singleton
public class ValidationBean implements ValidationBeanLocal{
    
    
    
    List<Integer> users_logged=new ArrayList();
  //  JsonObject logged=null;
    
    @Override
    public JsonObject addToValidated(User UserFound){    
        if (UserFound==null){
            return null;
        }
        else {
           Integer token=UserFound.getUsername().hashCode();
            JsonObject logged=Json.createObjectBuilder()
                    .add("UserID",UserFound.getUserID())
                    .add("Username",UserFound.getUsername())
                    .add("Role",UserFound.getUserRole())
                    .add("Token",token)
                    .build();
            if (!this.checkIfValidated(token)){
                users_logged.add(token);
            }
            return logged;
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
