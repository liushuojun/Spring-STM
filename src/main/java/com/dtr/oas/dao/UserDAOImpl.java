package com.dtr.oas.dao;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dtr.oas.exception.DuplicateUserException;
import com.dtr.oas.exception.UserNotFoundException;
import com.dtr.oas.model.User;
 
@Repository
public class UserDAOImpl implements UserDAO {
    static Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
 
    @Autowired
    private SessionFactory sessionFactory;
 
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
 
    @Override
    public void addUser(User user) throws DuplicateUserException {
    	logger.debug("UserDAOImpl.addUser() - [" + user.getUsername() + "]");
    	try{
    		User userCheck = getUser(user.getUsername());
    		String message = "The user [" + userCheck.getUsername() + "] already exists"; 
    		throw new DuplicateUserException(message);
    	} catch (UserNotFoundException e) {
        getCurrentSession().save(user);
     }
    }
 
    @Override
    public User getUser(int userId) throws UserNotFoundException {
        logger.debug("UserDAOImpl.getUser() - [" + userId + "]");
        logger.info("UserDAOImpl.getUser() - [" + userId + "]");
        User userObject = (User) getCurrentSession().get(User.class, userId);
         
        if(userId == 0)
        	return null;
        if (userObject == null) {
            throw new UserNotFoundException("User id [" + userId + "] not found");
        } else {
            return userObject;
        }
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public User getUser(String usersName) throws UserNotFoundException {
        logger.info("UserDAOImpl.getUser() - [" + usersName + "]");
        Query query = getCurrentSession().createQuery("from User where username = :sample ");
        query.setString("sample", usersName);
         
        logger.info(query.toString());
        if (query.list().size() == 0 ) {
            throw new UserNotFoundException("User [" + usersName + "] not found");
        } else {
            logger.debug("User List Size: " + query.list().size());
            List<User> list = (List<User>)query.list();
            User userObject = (User) list.get(0);
 
            return userObject;
        }
    }
 
    @Override
    public void updateUser(User user) throws UserNotFoundException, DuplicateUserException {
        User userCheck = getUser(user.getId());
         
        if (userCheck.getId() == user.getId()) {
            userCheck.setEnabled(user.getEnabled());
            userCheck.setPassword(user.getPassword());
            userCheck.setUsername(user.getUsername());
            userCheck.setRole(user.getRole());
            getCurrentSession().update(userCheck);
        } else {
            String message = "The user [" + userCheck.getUsername() + "] already exists";
            throw new DuplicateUserException(message);
        }
    }
 
    @Override
    public void deleteUser(int userId) throws UserNotFoundException {
        User user = getUser(userId);
        if (user != null) {
            getCurrentSession().delete(user);
        }
    }
 
    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        return getCurrentSession().createQuery("from User").list();
    }
}
