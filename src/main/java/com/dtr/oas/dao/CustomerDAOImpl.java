package com.dtr.oas.dao;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dtr.oas.exception.DuplicateCustomerException;
import com.dtr.oas.exception.CustomerNotFoundException;
import com.dtr.oas.model.Aftersaleslevel;
import com.dtr.oas.model.Customer;
import com.dtr.oas.model.Industry;
 
@Repository
public class CustomerDAOImpl implements CustomerDAO {
    static Logger logger = LoggerFactory.getLogger(CustomerDAOImpl.class);
 
    @Autowired
    private SessionFactory sessionFactory;
 
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
 
    @Override
    public void addCustomer(Customer customer) throws DuplicateCustomerException {
    	logger.info("CustomerDAOImpl.addCustomer() - [" + customer.getCustomername() + "]");
    	try{
    		Customer customerCheck = getCustomer(customer.getCustomername());
    		String message = "The Customer [" + customerCheck.getCustomername() + "] already exists"; 
    		throw new DuplicateCustomerException(message);
    	} catch (CustomerNotFoundException e) {
        getCurrentSession().save(customer);
     }
    }
 
    @Override
    public Customer getCustomer(int customerId) throws CustomerNotFoundException {
        logger.info("CustomerDAOImpl.getUser() - [" + customerId + "]");
        Customer customerObject = (Customer) getCurrentSession().get(Customer.class, customerId);
         
        if (customerObject == null) {
            throw new CustomerNotFoundException("Customer id [" + customerId + "] not found");
        } else {
            return customerObject;
        }
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public Customer getCustomer(String customerName) throws CustomerNotFoundException {
        logger.info("CustomerDAOImpl.getCustomer() - [" + customerName + "]");
        Query query = getCurrentSession().createQuery("from Customer where customername = :sampleName");
        query.setString("sampleName", customerName);
         
        logger.info(query.toString());
        if (query.list().size() == 0 ) {
            throw new CustomerNotFoundException("Customer [" + customerName + "] not found");
        } else {
            logger.info("Customer List Size: " + query.list().size());
            List<Customer> list = (List<Customer>)query.list();
            Customer customerObject = (Customer) list.get(0);
 
            return customerObject;
        }
    }
    
    @Override
    public Aftersaleslevel getLevel(int level) throws CustomerNotFoundException  {
    	logger.info("CustomerDAOImpl.getLevel()- [" + level + "]");
    	Aftersaleslevel customerlevel = (Aftersaleslevel) getCurrentSession().get(Aftersaleslevel.class, level);
    	
        if (customerlevel == null) {
            throw new CustomerNotFoundException("level id [" + level + "] not found");
        } else {
            return customerlevel;
        }
    }
    
    @Override
    public Industry getIndustry(int industryId) throws CustomerNotFoundException {
    	logger.info("CustomerDAOImpl.getIndustry()- [" + industryId + "]");
    	Industry industryobject = (Industry) getCurrentSession().get(Industry.class, industryId);
    	
        if (industryobject == null) {
            throw new CustomerNotFoundException("industry id [" + industryId + "] not found");
        } else {
            return industryobject;
        }
    }
 
    @Override
    public void updateCustomer(Customer customer) throws CustomerNotFoundException, DuplicateCustomerException {
        Customer customerCheck = getCustomer(customer.getId());
         
        if (customerCheck.getId() == customer.getId()) {
        	customerCheck.setCustomername(customer.getCustomername());
        	customerCheck.setEmployee_num(customer.getEmployee_num());
        	customerCheck.setLevel(customer.getLevel());
        	customerCheck.setIndustry(customer.getIndustry());
        	customerCheck.setAddress(customer.getAddress());
        	customerCheck.setBirthday(customer.getBirthday());
        	customerCheck.setHabit(customer.getHabit());
        	customerCheck.setTelephone(customer.getTelephone());
        	customerCheck.setLeadname(customer.getLeadname());
        	customerCheck.setOrdernumber(customer.getOrdernumber());
        	customerCheck.setComputer(customer.getComputer());
        	customerCheck.setTargetprice(customer.getTargetprice());
            getCurrentSession().update(customerCheck);
        } else {
            String message = "The Customer [" + customerCheck.getCustomername() + "] already exists";
            throw new DuplicateCustomerException(message);
        }
    }
 
    @Override
    public void deleteCustomer(int customerId) throws CustomerNotFoundException {
        Customer customer = getCustomer(customerId);
        if (customer != null) {
            getCurrentSession().delete(customer);
        }
    }
 
    @Override
    @SuppressWarnings("unchecked")
    public List<Customer> getCustomers() {
        return getCurrentSession().createQuery("from Customer").list();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Aftersaleslevel> getLevels() {
    	return getCurrentSession().createQuery("from Aftersaleslevel").list();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Industry> getIndustries() {
    	return getCurrentSession().createQuery("from Industry").list();
    }
    
/*    @Override
    public Aftersaleslevel getLevel(int customerId) throws CustomerNotFoundException {
    	Customer customer = getCustomer(customerId);
    	return customer.getLevel();
    }
    
    @Override
    public Industry getIndustry(int customerId) throws CustomerNotFoundException {
    	Customer customer = getCustomer(customerId);
    	return customer.getIndustry();
    }*/
    
}
