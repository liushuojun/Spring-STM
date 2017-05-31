package com.dtr.oas.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dtr.oas.exception.ComputerNotFoundException;
import com.dtr.oas.exception.CustomerNotFoundException;
import com.dtr.oas.exception.DuplicateCustomerException;
import com.dtr.oas.model.Aftersaleslevel;
import com.dtr.oas.model.Computer;
import com.dtr.oas.model.ComputerFetchEntity;
import com.dtr.oas.model.Computernumber;
import com.dtr.oas.model.ComputernumberEntity;
import com.dtr.oas.model.Customer;
import com.dtr.oas.model.CustomerEntity;
import com.dtr.oas.model.CustomerFetchEntity;
import com.dtr.oas.model.Industry;
import com.dtr.oas.model.Order;
import com.dtr.oas.model.OrderFetchFullEntity;
import com.dtr.oas.service.ComputerService;
import com.dtr.oas.service.CustomerService;
import com.dtr.oas.service.OrderService;
import com.google.common.collect.Lists;

@Controller
public class CustomerController {
    static Logger logger = LoggerFactory.getLogger(CustomerController.class);
    //static String businessObject = "customer"; //used in RedirectAttributes messages 

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ComputerService computerService;
    
    @Autowired
    private OrderService orderService;
    
    private boolean newflag;

	@RequestMapping (value="/customer/levels", method=RequestMethod.GET)
	@ResponseBody
    //@PreAuthorize("hasAnyRole('CTRL_USER_LIST_GET','CTRL_USER_EDIT_GET')")
    public List<Aftersaleslevel> getAllProcessors() {
    	logger.info("IN: Customer/list-GETLevel");
        return customerService.getLevels();
    }
	
	@RequestMapping (value="/customer/industries", method=RequestMethod.GET)
	@ResponseBody
    //@PreAuthorize("hasAnyRole('CTRL_USER_LIST_GET','CTRL_USER_EDIT_GET')")
    public List<Industry> getAllOSs() {
    	logger.info("IN: Customer/list-GETSystems");
        return customerService.getIndustries();
    }
    
    
	@RequestMapping (value="/customer/list", method=RequestMethod.GET)
	@ResponseBody
	public List<CustomerFetchEntity> ListofCustomers(@RequestParam(value="customerId") int base) {
		
		logger.info("IN: Customer/list-GET");
		if (getNewflag() == false) {
			return customerService.getCustomerFetchEntities(base);
		} else {
			List<CustomerFetchEntity> newcustomerFetchEntities = Lists.newArrayList();
			List<CustomerFetchEntity> customerFetchEntities = customerService.getCustomerFetchEntities(base);
			for(CustomerFetchEntity customerFetchEntity : customerFetchEntities) {
				if(customerFetchEntity.getOrdernumber() == 0)
					newcustomerFetchEntities.add(customerFetchEntity);
			}
			setNewflag(false);
			return newcustomerFetchEntities;
		}
	}
	
	@RequestMapping (value="/customer/orderlist", method=RequestMethod.GET)
	@ResponseBody
	public List<OrderFetchFullEntity> Listoforders(@RequestParam(value="customerId") int base) throws ComputerNotFoundException {
		
		logger.info("IN: Customer/list-customer-orderlist");
		List<Order> orders = orderService.getOrders();
		List<OrderFetchFullEntity> orderFetchFullEntities = Lists.newArrayList();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		for(Order order : orders) {
			if(order.getCustomer().getId() == base) {
				OrderFetchFullEntity orderFetchFullEntity = new OrderFetchFullEntity();
				
				orderFetchFullEntity.setBuilddate(ft.format(order.getBuilddate()));
				orderFetchFullEntity.setCustomer(order.getCustomer().getCustomername());
				orderFetchFullEntity.setId(order.getId());
				orderFetchFullEntity.setPrice(order.getPrice());
				orderFetchFullEntity.setStatus(order.getStatus().getType());
				if (order.getUser() != null) {
					orderFetchFullEntity.setUser(order.getUser().getUsername());
				}
				if (order.getComputerlist() != null) {
					List<ComputernumberEntity> computernumberEntities = Lists
							.newArrayList();
					for (Computernumber computernumber : order
							.getComputerlist()) {
						ComputernumberEntity computernumberEntity = new ComputernumberEntity();
						computernumberEntity.setId(computernumber.getId());
						computernumberEntity.setComputerId(computernumber
								.getComputerId());
						computernumberEntity.setComputername(computerService
								.getComputer(computernumber.getComputerId())
								.getComputername());
						computernumberEntity.setNumber(computernumber.getNumber());
						computernumberEntities.add(computernumberEntity);
					}
					orderFetchFullEntity
							.setComputerlist(computernumberEntities);
				}
				orderFetchFullEntities.add(orderFetchFullEntity);
			}
			
		}
		return orderFetchFullEntities;
	
		
	}
	
	@RequestMapping (value="/customer/addcustomer", method=RequestMethod.POST)
	@ResponseBody
	public void AddnewCustomer(@RequestBody CustomerEntity customerEntity) throws CustomerNotFoundException, DuplicateCustomerException, ComputerNotFoundException  {
		logger.info("IN: Customer/ADD");
        Customer customer =new Customer();
        customer.setCustomername(customerEntity.getCustomername());
        customer.setEmployee_num(customerEntity.getEmployee_num());
        customer.setIndustry(customerService.getIndustry(customerEntity.getIndustryId()));
        customer.setLevel(customerService.getLevel(customerEntity.getLevelId()));
        customer.setAddress(customerEntity.getAddress());
        customer.setBirthday(customerEntity.getBirthday());
        customer.setHabit(customerEntity.getHabit());
        customer.setTelephone(customerEntity.getTelephone());
        customer.setLeadname(customerEntity.getLeadname());
        customer.setOrdernumber(customerEntity.getOrdernumber());
        customer.setComputer(computerService.getComputer(customerEntity.getComputerId()));
        customer.setTargetprice(customerEntity.getTargetprice());
		customerService.addCustomer(customer);
	}
	
	@RequestMapping (value="/customer/updatecustomer", method=RequestMethod.POST)
	@ResponseBody
	public void UpdateCustomer(@RequestBody CustomerEntity customerEntity) throws CustomerNotFoundException, DuplicateCustomerException, ComputerNotFoundException  {
		logger.info("IN: Customer/update");
        Customer customer =new Customer();
        customer.setId(customerEntity.getId());
        customer.setCustomername(customerEntity.getCustomername());
        customer.setEmployee_num(customerEntity.getEmployee_num());
        customer.setIndustry(customerService.getIndustry(customerEntity.getIndustryId()));
        customer.setLevel(customerService.getLevel(customerEntity.getLevelId()));
        customer.setAddress(customerEntity.getAddress());
        customer.setBirthday(customerEntity.getBirthday());
        customer.setHabit(customerEntity.getHabit());
        customer.setTelephone(customerEntity.getTelephone());
        customer.setLeadname(customerEntity.getLeadname());
        customer.setOrdernumber(customerEntity.getOrdernumber());
        customer.setComputer(computerService.getComputer(customerEntity.getComputerId()));
        customer.setTargetprice(customerEntity.getTargetprice());
        
        logger.info(customer.toString());
		customerService.updateCustomer(customer);
	}
	
	@RequestMapping (value="/customer/delete", method=RequestMethod.DELETE)
	@ResponseBody
	public void DeleteCustomer(@RequestParam int id) throws CustomerNotFoundException {
		logger.info("IN: Customer/delete");
		customerService.deleteCustomer(id);
	}
	
	void setNewflag(boolean flag) {
		newflag = flag;
	}
	
	boolean getNewflag() {
		return newflag;
	}
	
	
	@RequestMapping (value="/customer/suggestedcomputer", method=RequestMethod.GET)
	@ResponseBody
	List<ComputerFetchEntity> getSuggestedComputers(@RequestParam(value="targetprice") int base) {
		logger.info("IN: Customer/suggestedcomputers"+ base);
		List<Computer> suggestedComputers = Lists.newArrayList();
		List<Computer> allComputers = computerService.getComputers();
		Computer suggestedcomputer = new Computer();
		List<ComputerFetchEntity> suggestedFetchComputers = Lists.newArrayList();
		List<ComputerFetchEntity> otherFetchComputers = Lists.newArrayList();
		
		for (int i = 0; i < 3; i++) {
			suggestedcomputer = getMinimum(allComputers, base);
			suggestedComputers.add(suggestedcomputer);
			allComputers.remove(suggestedcomputer);
		}
		
		for(Computer computer : suggestedComputers) {
    		ComputerFetchEntity computerFetchEntity = new ComputerFetchEntity();
    		computerFetchEntity.setId(computer.getId());
    		computerFetchEntity.setComputername(computer.getComputername());
    		computerFetchEntity.setDisplay(computer.getDisplay().getType());
    		computerFetchEntity.setMemory(computer.getMemory().getType());
    		computerFetchEntity.setPrice(computer.getPrice());
    		computerFetchEntity.setProcessor(computer.getProcessor().getType());
    		computerFetchEntity.setSeries(computer.getSeries());
    		computerFetchEntity.setSystem(computer.getSystem().getType());
            
    		suggestedFetchComputers.add(computerFetchEntity);
			}
		for(Computer computer : allComputers) {
    		ComputerFetchEntity computerFetchEntity = new ComputerFetchEntity();
    		computerFetchEntity.setId(computer.getId());
    		computerFetchEntity.setComputername(computer.getComputername());
    		computerFetchEntity.setDisplay(computer.getDisplay().getType());
    		computerFetchEntity.setMemory(computer.getMemory().getType());
    		computerFetchEntity.setPrice(computer.getPrice());
    		computerFetchEntity.setProcessor(computer.getProcessor().getType());
    		computerFetchEntity.setSeries(computer.getSeries());
    		computerFetchEntity.setSystem(computer.getSystem().getType());
            
    		otherFetchComputers.add(computerFetchEntity);
			}
		
    	logger.info(suggestedFetchComputers.toString());
    	if(base >= 0) {
    	return suggestedFetchComputers;
    	} else {
    		return otherFetchComputers;
    	}
		
	}
	
	@RequestMapping (value="/customer/othercomputer", method=RequestMethod.GET)
	@ResponseBody
	List<ComputerFetchEntity> getOtherComputers(@RequestParam(value="targetprice") int base) {
		logger.info("IN: Customer/suggestedcomputers"+ base);
		List<Computer> suggestedComputers = Lists.newArrayList();
		List<Computer> allComputers = computerService.getComputers();
		Computer suggestedcomputer = new Computer();
		List<ComputerFetchEntity> suggestedFetchComputers = Lists.newArrayList();
		List<ComputerFetchEntity> otherFetchComputers = Lists.newArrayList();
		
		for (int i = 0; i < 3; i++) {
			suggestedcomputer = getMinimum(allComputers, base);
			suggestedComputers.add(suggestedcomputer);
			allComputers.remove(suggestedcomputer);
		}
		
		for(Computer computer : allComputers) {
    		ComputerFetchEntity computerFetchEntity = new ComputerFetchEntity();
    		computerFetchEntity.setId(computer.getId());
    		computerFetchEntity.setComputername(computer.getComputername());
    		computerFetchEntity.setDisplay(computer.getDisplay().getType());
    		computerFetchEntity.setMemory(computer.getMemory().getType());
    		computerFetchEntity.setPrice(computer.getPrice());
    		computerFetchEntity.setProcessor(computer.getProcessor().getType());
    		computerFetchEntity.setSeries(computer.getSeries());
    		computerFetchEntity.setSystem(computer.getSystem().getType());
            
    		otherFetchComputers.add(computerFetchEntity);
			}
		
    	logger.info(suggestedFetchComputers.toString());
  
    		return otherFetchComputers;
		
	}
	
	Computer getMinimum(List<Computer> computers, int target) {
		int value = 1000;
		Computer suggestedcomputer = new Computer();
		for(Computer computer : computers) {
			if(Math.abs(computer.getPrice() - target)  < value) {
				value = Math.abs(computer.getPrice() - target);
				suggestedcomputer = computer;
			}
		}
		return suggestedcomputer;
	}
		
    
    @RequestMapping(value = "/customer")
    //@PreAuthorize("hasRole('CTRL_USER_LIST_GET')")
	public String customermanagement(@RequestParam(value="newcustomer") boolean newcustomer) {
    	logger.info("newcustomer = " + newcustomer);
    	setNewflag(newcustomer);
		return "customer-management";
	}
    
    
}
