package com.dtr.oas.dao;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dtr.oas.exception.DuplicateComputerException;
import com.dtr.oas.exception.ComputerNotFoundException;
import com.dtr.oas.model.Computer;
 
@Repository
public class ComputerDAOImpl implements ComputerDAO {
    static Logger logger = LoggerFactory.getLogger(ComputerDAOImpl.class);
 
    @Autowired
    private SessionFactory sessionFactory;
 
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
 
    @Override
    public void addComputer(Computer computer) throws DuplicateComputerException {
    	logger.info("ComputerDAOImpl.addComputer() - [" + computer.getComputername() + "]");
    	try{
    		Computer computerCheck = getComputer(computer.getComputername(),computer.getSeries());
    		String message = "The Computer [" + computerCheck.getComputername() + "] already exists"; 
    		throw new DuplicateComputerException(message);
    	} catch (ComputerNotFoundException e) {
        getCurrentSession().save(computer);
     }
    }
 
    @Override
    public Computer getComputer(int computerId) throws ComputerNotFoundException {
        logger.info("ComputerDAOImpl.getUser() - [" + computerId + "]");
        Computer computerObject = (Computer) getCurrentSession().get(Computer.class, computerId);
         
        if (computerObject == null) {
            throw new ComputerNotFoundException("Computer id [" + computerId + "] not found");
        } else {
            return computerObject;
        }
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public Computer getComputer(String computerName,String series) throws ComputerNotFoundException {
        logger.info("ComputerDAOImpl.getComputer() - [" + computerName + "]" + " [" + series +"]");
        Query query = getCurrentSession().createQuery("from Computer where computername = :sampleName and series = :sampleSeries");
        query.setString("sampleName", computerName);
        query.setString("sampleSeries", series);
         
        logger.info(query.toString());
        if (query.list().size() == 0 ) {
            throw new ComputerNotFoundException("Computer [" + computerName + "] not found");
        } else {
            logger.info("Computer List Size: " + query.list().size());
            List<Computer> list = (List<Computer>)query.list();
            Computer computerObject = (Computer) list.get(0);
 
            return computerObject;
        }
    }
 
    @Override
    public void updateComputer(Computer computer) throws ComputerNotFoundException, DuplicateComputerException {
        Computer computerCheck = getComputer(computer.getId());
         
        if (computerCheck.getId() == computer.getId()) {
        	computerCheck.setComputername(computer.getComputername());
        	computerCheck.setSeries(computer.getSeries());
        	computerCheck.setProcessor(computer.getProcessor());
        	computerCheck.setSystem(computer.getSystem());
        	computerCheck.setMemory(computer.getMemory());
        	computerCheck.setMemory(computer.getMemory());
        	computerCheck.setPrice(computer.getPrice());
            getCurrentSession().update(computerCheck);
        } else {
            String message = "The Computer [" + computerCheck.getComputername() + "] already exists";
            throw new DuplicateComputerException(message);
        }
    }
 
    @Override
    public void deleteComputer(int computerId) throws ComputerNotFoundException {
        Computer computer = getComputer(computerId);
        if (computer != null) {
            getCurrentSession().delete(computer);
        }
    }
 
    @Override
    @SuppressWarnings("unchecked")
    public List<Computer> getComputers() {
        return getCurrentSession().createQuery("from Computer").list();
    }
}