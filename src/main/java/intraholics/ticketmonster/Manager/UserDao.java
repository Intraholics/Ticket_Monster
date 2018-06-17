/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intraholics.ticketmonster.Manager;

import intraholics.ticketmonster.Entities.Cart;
import intraholics.ticketmonster.Entities.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author Kostis Hatzistamatis
 * Dao Class For Implementing All the functionalities regarding with the user Entity
 */

@Stateless
public class UserDao implements UserDaoLocal{
    
    @PersistenceContext
    private EntityManager em;

    /*Adds a User Object to the Database*/
    @Override
    public boolean addUser(User user1) {
        em.persist(user1);
        return true;
    }

    /*Finds a User by it's Unique ID number*/
    @Override
    public User findUserById(Integer ID) {
       return  em.find(User.class, ID);
    }

    /*Finds all User Objects from the Database*/
    @Override
    public List<User> findAllUsers() {
       return em.createNamedQuery("User.findAll").getResultList();
    }

    /*Deletes a User from the Database*/
    @Override
    public boolean deleteUserById(Integer Id) {
        User user = em.find(User.class, Id);
        em.remove(user);
        return true;
    }

    /*Updates a User to the Database*/
    @Override
    public boolean updateUser(User user) {
        em.merge(user);
        return true;
    }

    /*Finds a User by it's Username and Password*/
    @Override
    public User checkLoginCredentials(String username, String password) {
      Query query = em.createQuery("SELECT u FROM User u WHERE u.username=:usernam AND u.password=:pass",User.class);
      query.setParameter("usernam", username);
      query.setParameter("pass", password);
      User found=(User)query.getSingleResult();
      return found;
    }
    
}
