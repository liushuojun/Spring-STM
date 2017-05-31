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
import com.dtr.oas.model.System;
 
@Repository
public class SystemDAOImpl implements SystemDAO {
    static Logger logger = LoggerFactory.getLogger(SystemDAOImpl.class);
 
    @Autowired
    private SessionFactory sessionFactory;
 
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
 
    @Override
    public void addSystem(System system) throws DuplicateComputerPartsException {
        logger.info("SystemDAOImpl.addSystem() - [" + system.getType() + "]");
        try {
            // if the role is not found, then a ComputerPartsNotFoundException is
            // thrown from the getComputerParts method call, and the new role will be 
            // added.
            //
            // if the role is found, then the flow will continue from the getComputerParts
            // method call and the DuplicateComputerPartsException will be thrown.
            System systemCheck = getSystem(system.getType());
            String message = "The system [" + systemCheck.getType() + "] already exists";
            throw new DuplicateComputerPartsException(message);
        } catch (ComputerPartsNotFoundException e) {
            getCurrentSession().save(system);
        }
    }
 
    @Override
    public System getSystem(int system_id) throws ComputerPartsNotFoundException {
        System systemObject = (System) getCurrentSession().get(System.class, system_id);
        if (systemObject == null ) {
            throw new ComputerPartsNotFoundException("System id [" + system_id + "] not found");
        } else {
            return systemObject;
        }
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public System getSystem(String systemtype) throws ComputerPartsNotFoundException {
        logger.info("SystemDAOImpl.getSystem() - [" + systemtype + "]");
        Query query = getCurrentSession().createQuery("from System where type = :SystemType ");
        query.setString("SystemType", systemtype);
         
        logger.info(query.toString());
        if (query.list().size() == 0 ) {
            throw new ComputerPartsNotFoundException("System [" + systemtype + "] not found");
        } else {
            logger.info("System List Size: " + query.list().size());
            List<System> list = (List<System>)query.list();
            System roleObject = (System) list.get(0);
 
            return roleObject;
        }
    }
 
    @Override
    public void updateSystem(System system) throws ComputerPartsNotFoundException {
        System systemToUpdate = getSystem(system.getId());
        systemToUpdate.setId(system.getId());
        systemToUpdate.setType(system.getType());
        systemToUpdate.setPrice(system.getPrice());
        getCurrentSession().update(systemToUpdate);
    }
 
    @Override
    public void deleteSystem(int id) throws ComputerPartsNotFoundException {
        System system = getSystem(id);
        if (system != null) {
            getCurrentSession().delete(system);
        }
    }
 
    @Override
    @SuppressWarnings("unchecked")
    public List<System> getSystems() {
        return getCurrentSession().createQuery("from System").list();
    }
}