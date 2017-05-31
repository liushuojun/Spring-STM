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
import com.dtr.oas.model.Memory;
 
@Repository
public class MemoryDAOImpl implements MemoryDAO {
    static Logger logger = LoggerFactory.getLogger(MemoryDAOImpl.class);
 
    @Autowired
    private SessionFactory sessionFactory;
 
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
 
    @Override
    public void addMemory(Memory memory) throws DuplicateComputerPartsException {
        logger.info("MemoryDAOImpl.addMemory() - [" + memory.getType() + "]");
        try {
            // if the role is not found, then a ComputerPartsNotFoundException is
            // thrown from the getComputerParts method call, and the new role will be 
            // added.
            //
            // if the role is found, then the flow will continue from the getComputerParts
            // method call and the DuplicateComputerPartsException will be thrown.
            Memory memoryCheck = getMemory(memory.getType());
            String message = "The memory [" + memoryCheck.getType() + "] already exists";
            throw new DuplicateComputerPartsException(message);
        } catch (ComputerPartsNotFoundException e) {
            getCurrentSession().save(memory);
        }
    }
 
    @Override
    public Memory getMemory(int memory_id) throws ComputerPartsNotFoundException {
        Memory memoryObject = (Memory) getCurrentSession().get(Memory.class, memory_id);
        if (memoryObject == null ) {
            throw new ComputerPartsNotFoundException("Memory id [" + memory_id + "] not found");
        } else {
            return memoryObject;
        }
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public Memory getMemory(String memorytype) throws ComputerPartsNotFoundException {
        logger.info("MemoryDAOImpl.getMemory() - [" + memorytype + "]");
        Query query = getCurrentSession().createQuery("from Memory where type = :MemoryType ");
        query.setString("MemoryType", memorytype);
         
        logger.info(query.toString());
        if (query.list().size() == 0 ) {
            throw new ComputerPartsNotFoundException("Memory [" + memorytype + "] not found");
        } else {
            logger.info("Memory List Size: " + query.list().size());
            List<Memory> list = (List<Memory>)query.list();
            Memory roleObject = (Memory) list.get(0);
 
            return roleObject;
        }
    }
 
    @Override
    public void updateMemory(Memory memory) throws ComputerPartsNotFoundException {
        Memory memoryToUpdate = getMemory(memory.getId());
        memoryToUpdate.setId(memory.getId());
        memoryToUpdate.setType(memory.getType());
        memoryToUpdate.setPrice(memory.getPrice());
        getCurrentSession().update(memoryToUpdate);
    }
 
    @Override
    public void deleteMemory(int id) throws ComputerPartsNotFoundException {
        Memory memory = getMemory(id);
        if (memory != null) {
            getCurrentSession().delete(memory);
        }
    }
 
    @Override
    @SuppressWarnings("unchecked")
    public List<Memory> getMemorys() {
        return getCurrentSession().createQuery("from Memory").list();
    }
}