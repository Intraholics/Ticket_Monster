/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intraholics.ticketmonster.Business;

import intraholics.ticketmonster.Entities.User;
import intraholics.ticketmonster.SupplementaryClasses.UserLogged;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Kostis
 */
@Local
public interface UsersBusinessLocal {
    public List<User> findAllUsers();
    public User findUserById(Integer UserID);
    public UserLogged logUser(String Username, String Password);
    public boolean addUsers(User user);
    public boolean updateUsers(User user);
    public boolean updateUsers(Integer UserID,boolean UserRole)throws Exception;
    public boolean deleteUsers(Integer UserID);
}
