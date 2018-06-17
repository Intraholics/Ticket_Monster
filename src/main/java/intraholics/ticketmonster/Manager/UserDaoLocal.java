/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intraholics.ticketmonster.Manager;

import intraholics.ticketmonster.Entities.User;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Kostis
 */
@Local
public interface UserDaoLocal {
    
    
    public boolean addUser(User user);
    public User findUserById(Integer ID);
    public List<User> findAllUsers();
    public boolean deleteUserById(Integer Id);
    public boolean updateUser(User user);
    public User checkLoginCredentials(String username,String password);
    
}
