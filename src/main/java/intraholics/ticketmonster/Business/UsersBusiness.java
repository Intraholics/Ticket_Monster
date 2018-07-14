/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intraholics.ticketmonster.Business;

import intraholics.ticketmonster.Entities.User;
import intraholics.ticketmonster.Mail.EmailSessionBean;
import intraholics.ticketmonster.Manager.UserDaoLocal;
import intraholics.ticketmonster.SupplementaryClasses.UserLogged;
import intraholics.ticketmonster.security.Encryption;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;
import javax.inject.Inject;


/**
 *
 * @author Kostis
 * 
 * 
 */




@Stateless
public class UsersBusiness implements UsersBusinessLocal {
    
@Inject
private UserDaoLocal user1;
@Inject
private Encryption encryted;
@Inject
private EmailSessionBean mail; 



    @Override
    public List<User> findAllUsers() {
        return user1.findAllUsers();
    }

    @Override
    public User findUserById(Integer UserID) {
        return user1.findUserById(UserID);
    }

    @Override
    public UserLogged logUser(String Username, String Password) {
        User found=user1.checkLoginCredentials(Username, Password);
        if (found!=null){
        UserLogged founduser=new UserLogged();
        founduser.setUserID(found.getUserID());
        founduser.setUsername(found.getUsername());
        founduser.setUserRole(found.getUserRole());
        return founduser;
        }
        else{
            return null;
        }
    }

    @Override
    public boolean addUsers(User user) {
        user.setPassword(encryted.encryptMessage(user.getPassword()));
        user1.addUser(user);
        return true;
    }

    @Override
    public boolean updateUsers(User user) {
        user1.updateUser(user);
        return true;
    }

    @Override
    public boolean updateUsers(Integer UserID, boolean UserRole) throws Exception {
         if(UserRole==true){
               User usertoupdate=user1.findUserById(UserID);
               boolean roletoggle=usertoupdate.getUserRole();
                   usertoupdate.setUserRole(!roletoggle);
                   user1.updateUser(usertoupdate);
            }
            else {
            String newpass= PasswordGenerator();
            String npass =encryted.encryptMessage(newpass);
            String email = user1.findUserById(UserID).getEmail();
            user1.updateUser(UserID,npass);
//            mail.SendMail(email, newpass);
            }
         return true;
    }

    @Override
    public boolean deleteUsers(Integer UserID) {
        user1.deleteUserById(UserID);
        return true;
    }
    
    
    
     /*Random Number password generator*/
     public String PasswordGenerator()
    {
    	String s = "";
		Random random = new Random();
        for (int i = 0; i < 12; i++)
        {
            int randomNumber = random.nextInt(122) + 44;
            char c = (char)randomNumber;            
            s = s + Character.toString(c);            
        } 
        return s;
    }


}
