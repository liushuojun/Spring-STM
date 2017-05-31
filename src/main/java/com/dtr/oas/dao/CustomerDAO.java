package com.dtr.oas.dao;
import java.util.List;

import com.dtr.oas.exception.DuplicateCustomerException;
import com.dtr.oas.exception.CustomerNotFoundException;
import com.dtr.oas.model.Aftersaleslevel;
import com.dtr.oas.model.Customer;
import com.dtr.oas.model.Industry;
 
public interface CustomerDAO {
 
    public void addCustomer(Customer Customer) throws DuplicateCustomerException;
 
    public Customer getCustomer(int customerId) throws CustomerNotFoundException;
 
    public Customer getCustomer(String customername) throws CustomerNotFoundException;
 
    public void updateCustomer(Customer customer) throws CustomerNotFoundException, DuplicateCustomerException;
 
    public void deleteCustomer(int customerId) throws CustomerNotFoundException;
 
    public List<Customer> getCustomers();
    
    public List<Aftersaleslevel> getLevels();
    
    public List<Industry> getIndustries();
    
    public Aftersaleslevel getLevel(int level) throws CustomerNotFoundException;
    
    public Industry getIndustry(int industry) throws CustomerNotFoundException;
}
