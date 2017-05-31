package com.dtr.oas.service;
import java.util.List;






import com.dtr.oas.model.Aftersaleslevel;
import com.dtr.oas.model.Customer;
import com.dtr.oas.model.CustomerFetchEntity;
import com.dtr.oas.model.Industry;
import com.dtr.oas.exception.DuplicateCustomerException;
import com.dtr.oas.exception.CustomerNotFoundException;
 
public interface CustomerService {
 
    public void addCustomer(Customer customer) throws DuplicateCustomerException;
 
    public Customer getCustomer(int customerId) throws CustomerNotFoundException;
 
    public Customer getCustomer(String customername) throws CustomerNotFoundException;
 
    public void updateCustomer(Customer customer) throws CustomerNotFoundException, DuplicateCustomerException;
 
    public void deleteCustomer(int customerId) throws CustomerNotFoundException;
 
    public List<Customer> getCustomers();
    
    public List<CustomerFetchEntity> getCustomerFetchEntities(int base);
    
    public List<Aftersaleslevel> getLevels();
    
    public List<Industry> getIndustries();
    
    public Aftersaleslevel getLevel(int level_ID) throws CustomerNotFoundException;
    
    public Industry getIndustry(int industry_ID) throws CustomerNotFoundException;

}