/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intraholics.ticketmonster.Manager;

import intraholics.ticketmonster.Entities.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Kostis
 */
@Stateless
public class UserDao implements UserDaoLocal{
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public boolean addUser(User user1) {
        em.persist(user1);
        return true;
    }

    @Override
    public User findUserById(Integer ID) {
       return  em.find(User.class, ID);
    }

    @Override
    public List<User> findAllUsers() {
       return em.createNamedQuery("User.findAll").getResultList();
    }

    @Override
    public boolean deleteUserById(Integer Id) {
        User user = em.find(User.class, Id);
        em.remove(user);
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        em.merge(user);
        return true;
    }

    @Override
    public User checkLoginCredentials(String username, String password) {
      Query query = em.createQuery("SELECT u FROM User u WHERE u.username=:usernam AND u.password=:pass",User.class);
      query.setParameter("usernam", username);
      query.setParameter("pass", password);
      User found=(User)query.getSingleResult();
      return found;
    }
    
    
    
}
