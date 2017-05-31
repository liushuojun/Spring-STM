package com.dtr.oas.dao;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dtr.oas.exception.DuplicateComputerPartsException;
import com.dtr.oas.exception.ComputerPartsNotFoundException;
import com.dtr.oas.model.Display;
 
@Repository
public class DisplayDAOImpl implements DisplayDAO {
    static Logger logger = LoggerFactory.getLogger(DisplayDAOImpl.class);
 
    @Autowired
    private SessionFactory sessionFactory;
 
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
 
    @Override
    public void addDisplay(Display display) throws DuplicateComputerPartsException {
        logger.info("DisplayDAOImpl.addDisplay() - [" + display.getType() + "]");
        try {
            // if the role is not found, then a ComputerPartsNotFoundException is
            // thrown from the getComputerParts method call, and the new role will be 
            // added.
            //
            // if the role is found, then the flow will continue from the getComputerParts
            // method call and the DuplicateComputerPartsException will be thrown.
            Display displayCheck = getDisplay(display.getType());
            String message = "The display [" + displayCheck.getType() + "] already exists";
            throw new DuplicateComputerPartsException(message);
        } catch (ComputerPartsNotFoundException e) {
            getCurrentSession().save(display);
        }
    }
 
    @Override
    public Display getDisplay(int display_id) throws ComputerPartsNotFoundException {
        Display displayObject = (Display) getCurrentSession().get(Display.class, display_id);
        if (displayObject == null ) {
            throw new ComputerPartsNotFoundException("Display id [" + display_id + "] not found");
        } else {
            return displayObject;
        }
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public Display getDisplay(String displaytype) throws ComputerPartsNotFoundException {
        logger.info("DisplayDAOImpl.getDisplay() - [" + displaytype + "]");
        Query query = getCurrentSession().createQuery("from Display where type = :DisplayType ");
        query.setString("DisplayType", displaytype);
         
        logger.info(query.toString());
        if (query.list().size() == 0 ) {
            throw new ComputerPartsNotFoundException("Display [" + displaytype + "] not found");
        } else {
            logger.info("Display List Size: " + query.list().size());
            List<Display> list = (List<Display>)query.list();
            Display roleObject = (Display) list.get(0);
 
            return roleObject;
        }
    }
 
    @Override
    public void updateDisplay(Display display) throws ComputerPartsNotFoundException {
        Display displayToUpdate = getDisplay(display.getId());
        displayToUpdate.setId(display.getId());
        displayToUpdate.setType(display.getType());
        displayToUpdate.setPrice(display.getPrice());
        getCurrentSession().update(displayToUpdate);
    }
 
    @Override
    public void deleteDisplay(int id) throws ComputerPartsNotFoundException {
        Display display = getDisplay(id);
        if (display != null) {
            getCurrentSession().delete(display);
        }
    }
 
    @Override
    @SuppressWarnings("unchecked")
    public List<Display> getDisplays() {
        return getCurrentSession().createQuery("from Display").list();
    }
}
