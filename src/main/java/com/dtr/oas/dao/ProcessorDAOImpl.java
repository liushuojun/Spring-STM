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
import com.dtr.oas.model.Processor;
 
@Repository
public class ProcessorDAOImpl implements ProcessorDAO {
    static Logger logger = LoggerFactory.getLogger(ProcessorDAOImpl.class);
 
    @Autowired
    private SessionFactory sessionFactory;
 
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
 
    @Override
    public void addProcessor(Processor processor) throws DuplicateComputerPartsException {
        logger.info("ProcessorDAOImpl.addProcessor() - [" + processor.getType() + "]");
        try {
            // if the role is not found, then a RoleNotFoundException is
            // thrown from the getRole method call, and the new role will be 
            // added.
            //
            // if the role is found, then the flow will continue from the getRole
            // method call and the DuplicateRoleException will be thrown.
            Processor processorCheck = getProcessor(processor.getType());
            String message = "The processor [" + processorCheck.getType() + "] already exists";
            throw new DuplicateComputerPartsException(message);
        } catch (ComputerPartsNotFoundException e) {
            getCurrentSession().save(processor);
        }
    }
 
    @Override
    public Processor getProcessor(int processor_id) throws ComputerPartsNotFoundException {
        Processor processorObject = (Processor) getCurrentSession().get(Processor.class, processor_id);
        if (processorObject == null ) {
            throw new ComputerPartsNotFoundException("Processor id [" + processor_id + "] not found");
        } else {
            return processorObject;
        }
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public Processor getProcessor(String processortype) throws ComputerPartsNotFoundException {
        logger.info("ProcessorDAOImpl.getProcessor() - [" + processortype + "]");
        Query query = getCurrentSession().createQuery("from Processor where type = :ProcessorType ");
        query.setString("ProcessorType", processortype);
         
        logger.info(query.toString());
        if (query.list().size() == 0 ) {
            throw new ComputerPartsNotFoundException("Processor [" + processortype + "] not found");
        } else {
            logger.info("Processor List Size: " + query.list().size());
            List<Processor> list = (List<Processor>)query.list();
            Processor roleObject = (Processor) list.get(0);
 
            return roleObject;
        }
    }
 
    @Override
    public void updateProcessor(Processor processor) throws ComputerPartsNotFoundException {
        Processor processorToUpdate = getProcessor(processor.getId());
        processorToUpdate.setId(processor.getId());
        processorToUpdate.setType(processor.getType());
        processorToUpdate.setPrice(processor.getPrice());
        getCurrentSession().update(processorToUpdate);
    }
 
    @Override
    public void deleteProcessor(int id) throws ComputerPartsNotFoundException {
        Processor processor = getProcessor(id);
        if (processor != null) {
            getCurrentSession().delete(processor);
        }
    }
 
    @Override
    @SuppressWarnings("unchecked")
    public List<Processor> getProcessors() {
        return getCurrentSession().createQuery("from Processor").list();
    }
}
