package com.dtr.oas.service;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dtr.oas.dao.CustomerDAO;
import com.dtr.oas.exception.CustomerNotFoundException;
import com.dtr.oas.exception.DuplicateCustomerException;
import com.dtr.oas.model.Aftersaleslevel;
import com.dtr.oas.model.Customer;
import com.dtr.oas.model.CustomerFetchEntity;
import com.dtr.oas.model.Industry;
import com.google.common.collect.Lists;
 
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    static Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
     
    @Autowired
    private CustomerDAO customerDAO;    
    
 
    @Override
    public void addCustomer(Customer customer) throws DuplicateCustomerException {
    	customerDAO.addCustomer(customer);;
    }
 
    @Override
    public Customer getCustomer(int customerId) throws CustomerNotFoundException {
        return customerDAO.getCustomer(customerId);
    }
 
    @Override
    public Customer getCustomer(String customername) throws CustomerNotFoundException {
        return customerDAO.getCustomer(customername);
    }
 
    @Override
    public void updateCustomer(Customer customer) throws CustomerNotFoundException, DuplicateCustomerException {
    	customerDAO.updateCustomer(customer);
    }
 
    @Override
    public void deleteCustomer(int customerId) throws CustomerNotFoundException {
    	customerDAO.deleteCustomer(customerId);
    }
 
    @Override
    public List<Customer> getCustomers() {
        return customerDAO.getCustomers();
    }
    
    @Override
    public List<CustomerFetchEntity> getCustomerFetchEntities(int customerId) {
    	List<Customer> customers = customerDAO.getCustomers();
    	List<CustomerFetchEntity> customerEntities = Lists.newArrayList();
    	//CustomerFetchEntity customerFetchEntity = new CustomerFetchEntity();
    	for(Customer customer : customers){
    		CustomerFetchEntity customerFetchEntity = new CustomerFetchEntity();
    		customerFetchEntity.setId(customer.getId());
    		customerFetchEntity.setCustomername(customer.getCustomername());
    		customerFetchEntity.setEmployee_num(customer.getEmployee_num());
    		customerFetchEntity.setLevel(customer.getLevel().getLevel());
    		customerFetchEntity.setIndustry(customer.getIndustry().getIndustry());
    		customerFetchEntity.setLeadname(customer.getLeadname());
    		customerFetchEntity.setAddress(customer.getAddress());
    		customerFetchEntity.setBirthday(customer.getBirthday());
    		customerFetchEntity.setHabit(customer.getHabit());
            customerFetchEntity.setTelephone(customer.getTelephone());
            customerFetchEntity.setOrdernumber(customer.getOrdernumber());
            customerFetchEntity.setComputer(customer.getComputer().getComputername());
            customerFetchEntity.setTargetprice(customer.getTargetprice());
    		
			if (customerId == 0 || customerFetchEntity.getId() == customerId) {
				customerEntities.add(customerFetchEntity);
			}
    	}
    	//logger.info(customerFetchEntity.toString());
    	return customerEntities;
    }
    
    @Override
    public List<Aftersaleslevel> getLevels() {
    	return customerDAO.getLevels();
    }
    
    @Override
    public List<Industry> getIndustries() {
    	return customerDAO.getIndustries();
    }
    
    @Override
    public Aftersaleslevel getLevel(int customer_ID) throws CustomerNotFoundException {
    	return customerDAO.getLevel(customer_ID);
    }
    
    @Override
    public Industry getIndustry(int customer_ID) throws CustomerNotFoundException {
    	return customerDAO.getIndustry(customer_ID);
    }
    
    /* 
    //TODO Dummy role added temporarily until next example
    @Override
    public CustomerDetails loadCustomerByCustomername(String username) throws CustomernameNotFoundException {
        try {
            return getCustomer(username);
        } catch (CustomerNotFoundException e) {
            throw new CustomernameNotFoundException(e.getMessage());
        }
    }
    */
}

