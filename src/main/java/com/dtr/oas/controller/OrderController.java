package com.dtr.oas.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
import com.dtr.oas.exception.UserNotFoundException;
import com.dtr.oas.model.Computer;
import com.dtr.oas.model.ComputerFetchEntity;
import com.dtr.oas.model.Computernumber;
import com.dtr.oas.model.ComputernumberEntity;
import com.dtr.oas.model.Customer;
import com.dtr.oas.model.Order;
import com.dtr.oas.model.OrderEntity;
import com.dtr.oas.model.OrderFetchEntity;
import com.dtr.oas.model.OrderFetchFullEntity;
import com.dtr.oas.model.OrdertimeEntity;
import com.dtr.oas.model.OrdertimeFetchFullEntity;
import com.dtr.oas.model.OrdertimeNumber;
import com.dtr.oas.model.Report;
import com.dtr.oas.model.Status;
import com.dtr.oas.model.Target;
import com.dtr.oas.model.User;
import com.dtr.oas.model.UserFetchEntity;
import com.dtr.oas.service.ComputerService;
import com.dtr.oas.service.CustomerService;
import com.dtr.oas.service.OrderService;
import com.dtr.oas.service.TargetService;
import com.dtr.oas.service.UserService;
import com.google.common.collect.Lists;

@Controller
public class OrderController {
    static Logger logger = LoggerFactory.getLogger(OrderController.class);
    //static String businessObject = "order"; //used in RedirectAttributes messages 
    
    int orderId = 0;

    @Autowired
    private OrderService orderService;

   // @Autowired
    //private MessageSource messageSource;
    @Autowired
    private ComputerService computerService;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private TargetService targetService;

	@RequestMapping (value="/order/status", method=RequestMethod.GET)
	@ResponseBody
    //@PreAuthorize("hasAnyRole('CTRL_USER_LIST_GET','CTRL_USER_EDIT_GET')")
    public List<Status> getAllStatus() {
    	logger.info("IN: Order/list-GET Status");
    	if(LinkController.getCurrentUserId() == 5) {
    		return orderService.getTypes();
    	} else {
    		List<Status> staffstatus = Lists.newArrayList();
    		List<Status> totalstatus = orderService.getTypes();
    		staffstatus.add(totalstatus.get(2));
    		staffstatus.add(totalstatus.get(3));
    		staffstatus.add(totalstatus.get(4));   		
    		return staffstatus;
    	}
        
    }
	
	@RequestMapping (value="/order/users", method=RequestMethod.GET)
	@ResponseBody
    public List<UserFetchEntity> getAllUsers() {
    	logger.info("IN: Order/list-GET Users");
        List<User>  users = userService.getUsers();
        List<UserFetchEntity> userFetchEntities = Lists.newArrayList();
        for(User user: users) {
        	UserFetchEntity userFetchEntity = new UserFetchEntity();
        	userFetchEntity.setId(user.getId());
        	userFetchEntity.setUsername(user.getUsername());
        	
        	userFetchEntities.add(userFetchEntity);
        }
        return userFetchEntities;
    }
	
	@RequestMapping (value="/order/suggestedusers", method=RequestMethod.GET)
	@ResponseBody
    public List<UserFetchEntity> getSuggestedUsers() {
    	logger.info("IN: Order/list-GET Users");
        List<User>  users = userService.getUsers();
        List<UserFetchEntity> userFetchEntities = Lists.newArrayList();
        for(User user: users) {
        	
        	if(user.getRole().getId() == 2) {  // 2 is the id of role_sales
        		
            	UserFetchEntity userFetchEntity = new UserFetchEntity();
            	userFetchEntity.setId(user.getId());
            	userFetchEntity.setUsername(user.getUsername());
            	
            	userFetchEntities.add(userFetchEntity);
        	}

        }
        return userFetchEntities;
    }
	
	
	
	@RequestMapping (value="/order/computers", method=RequestMethod.GET)
	@ResponseBody
    //@PreAuthorize("hasAnyRole('CTRL_USER_LIST_GET','CTRL_USER_EDIT_GET')")
    public List<ComputerFetchEntity> getAllComputers(@RequestParam(value="computerId") int base) {
    	logger.info("IN: Order/list-GET Computers");
    	List<Computer> computers = computerService.getComputers();
    	List<ComputerFetchEntity> computerFetchEntities = Lists.newArrayList();
    	
    	for(Computer computer : computers) {
    		ComputerFetchEntity computerFetchEntity = new ComputerFetchEntity();
    		computerFetchEntity.setId(computer.getId());
    		computerFetchEntity.setComputername(computer.getComputername());
    		computerFetchEntity.setDisplay(computer.getDisplay().getType());
    		computerFetchEntity.setMemory(computer.getMemory().getType());
    		computerFetchEntity.setPrice(computer.getPrice());
    		computerFetchEntity.setProcessor(computer.getProcessor().getType());
    		computerFetchEntity.setSeries(computer.getSeries());
    		computerFetchEntity.setSystem(computer.getSystem().getType());

			if (base == 0 || computerFetchEntity.getId() == base) {
				computerFetchEntities.add(computerFetchEntity);
			}
    	}
    	
    	return computerFetchEntities;
    }
	
	@RequestMapping (value="/order/delete", method=RequestMethod.DELETE)
	@ResponseBody
	public void DeleteNewOrder(@RequestParam int id) {
		logger.info("IN: Order/delete");
		orderService.deleteOrder(id);
	}
	
	@RequestMapping (value="/order/addorder", method=RequestMethod.POST)
	@ResponseBody
    //@PreAuthorize("hasAnyRole('CTRL_USER_LIST_GET','CTRL_USER_EDIT_GET')")
    public void addNewOrder(@RequestBody OrderEntity orderEntity ) throws ComputerNotFoundException, CustomerNotFoundException, UserNotFoundException {
    	logger.info("IN: Order/list-Add order");
    	logger.info(orderEntity.toString());
    	Order order = new Order();
    	order.setBuilddate(orderEntity.getBuilddate());
    	//order.setComputer(computerService.getComputer(orderEntity.getComputerId()));
    	order.setCustomer(customerService.getCustomer(orderEntity.getCustomerId()));
        //order.setNumber(orderEntity.getNumber());
    	order.setComputerlist(orderEntity.getComputerlist());
    	List<Computernumber> computernumbers = orderEntity.getComputerlist();
    	int sumprice = 0;
    	for(Computernumber computernum : computernumbers) {
    		sumprice += computerService.getComputer(computernum.getComputerId()).getPrice() * computernum.getNumber();
    	}
        order.setPrice(sumprice);
        order.setReason(orderEntity.getReason());
        order.setComputerlist(orderEntity.getComputerlist());
        order.setStatus(orderService.getStatus(1));
        order.setUser(userService.getUser(orderEntity.getUserId()));
        
        orderService.addOrder(order);
    }
	
	@RequestMapping (value="/order/updateorder", method=RequestMethod.POST)
	@ResponseBody
    //@PreAuthorize("hasAnyRole('CTRL_USER_LIST_GET','CTRL_USER_EDIT_GET')")
    public void editNewOrder(@RequestBody OrderEntity orderEntity ) throws ComputerNotFoundException, CustomerNotFoundException, UserNotFoundException {
    	logger.info("IN: Order/list-Add order");
    	Order order = new Order();
    	order.setId(orderEntity.getId());
    	order.setBuilddate(orderEntity.getBuilddate());
    	//order.setComputer(computerService.getComputer(orderEntity.getComputerId()));
    	order.setCustomer(customerService.getCustomer(orderEntity.getCustomerId()));
        //order.setNumber(orderEntity.getNumber());
        //order.setPrice(computerService.getComputer(orderEntity.getComputerId()).getPrice() * orderEntity.getNumber());
        order.setReason(orderEntity.getReason());
        order.setStatus(orderService.getStatus(1));
        //order.setUser(userService.getUser(orderEntity.getUserId()));
        
        orderService.updateOrder(order);
    }
	
	
	@RequestMapping (value="/order/approvalorder", method=RequestMethod.POST)
	@ResponseBody
    //@PreAuthorize("hasAnyRole('CTRL_USER_LIST_GET','CTRL_USER_EDIT_GET')")
    public void approvalNewOrder(@RequestBody OrderEntity orderEntity ) throws ComputerNotFoundException, CustomerNotFoundException, UserNotFoundException {
    	logger.info("IN: Order/list-Add order");
    	logger.info(orderEntity.toString());
    	Order order = new Order();
    	order.setId(orderEntity.getId());
    	order.setBuilddate(orderEntity.getBuilddate());
    	order.setCustomer(customerService.getCustomer(orderEntity.getCustomerId()));
    	order.setPrice(orderEntity.getPrice());
    	order.setComputerlist(orderEntity.getComputerlist());
        order.setReason(orderEntity.getReason());
        order.setStatus(orderService.getStatus(orderEntity.getStatusId()));
        order.setUser(userService.getUser(orderEntity.getUserId()));
        
        orderService.updateOrder(order);
        
		if (orderEntity.getStatusId() != 2 && orderEntity.getUserId() != 0) {
			Target target = new Target();
			String str = "please start to follow order " + orderEntity.getId()
					+ " for customer " + order.getCustomer().getCustomername();
			target.setDetails(str);
			target.setStart(new Date());
			target.setTitle("order Id " + orderEntity.getId()
					+ " start to follow");
			target.setUser(userService.getUser(orderEntity.getUserId()));

			targetService.addTarget(target);
		}
    }
	
	@RequestMapping (value="/order/generate", method=RequestMethod.GET)
	@ResponseBody
	public int InsertOrdertoDB(){
    	List<Customer> customers = customerService.getCustomers();
    	List<Computer> Computers = computerService.getComputers();
    	int cs_size = customers.size();
    	int cm_size = Computers.size();
    	Random r = new Random();
    	for(int i = 0; i < 10000; i++){
        	Order order = new Order();
        	order.setBuilddate(new Date());
        	order.setCustomer(customers.get(r.nextInt(cs_size)));
        	List<Computernumber> cm_list = new ArrayList<Computernumber>();
			for (int k = 0; k < 2; k++) {
				Computernumber example = new Computernumber();
				example.setComputerId(Computers.get(r.nextInt(cm_size)).getId());
				example.setNumber(r.nextInt(50) + 1);
				cm_list.add(example);
			}
        	order.setComputerlist(cm_list);
        	int sumprice = 0;
        	for(Computernumber computernum : cm_list) {
        		try {
					sumprice += computerService.getComputer(computernum.getComputerId()).getPrice() * computernum.getNumber();
				} catch (ComputerNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        	order.setPrice(sumprice);
        	String reason = new String();
        	for(int j = 0; j < 10; j++){
        		reason += (char)('a' + r.nextInt(25));
        	}
        	order.setReason(reason);
        	order.setStatus(orderService.getStatus(5));
        	try {
				order.setUser(userService.getUser(6));
			} catch (UserNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	orderService.addOrder(order);
    	}
    	return 10000;
	}
	
	
	@RequestMapping (value="/order/updatestatus", method=RequestMethod.POST)
	@ResponseBody
    //@PreAuthorize("hasAnyRole('CTRL_USER_LIST_GET','CTRL_USER_EDIT_GET')")
	public void updateOrderStatus(@RequestBody OrderEntity orderEntity)
			throws ComputerNotFoundException, CustomerNotFoundException,
			UserNotFoundException {
		logger.info("IN: Order/list-Add order");
		logger.info(orderEntity.toString());
		Order order = new Order();
		order.setId(orderEntity.getId());
		order.setBuilddate(orderEntity.getBuilddate());		
		order.setCustomer(customerService.getCustomer(orderEntity.getCustomerId()));
		order.setPrice(orderEntity.getPrice());
		order.setComputerlist(orderEntity.getComputerlist());
		order.setReason(orderEntity.getReason());
		order.setStatus(orderService.getStatus(orderEntity.getStatusId()));
		order.setUser(userService.getUser(orderEntity.getUserId()));

		orderService.updateOrder(order);

		Target target = new Target();
		if (orderEntity.getStatusId() == 4) {
			String str = "The order Id " + orderEntity.getId()
					+ " for customer " + order.getCustomer().getCustomername()
					+ " is failed";
			target.setDetails(str);
			target.setStart(new Date());
			target.setTitle("order Id " + orderEntity.getId() + " failed");
			target.setUser(userService.getUser(5));

			targetService.addTarget(target);
		} else {

			String str = "The order Id " + orderEntity.getId()
					+ " for customer " + order.getCustomer().getCustomername()
					+ " is successful";
			target.setDetails(str);
			target.setStart(new Date());
			target.setTitle("order Id " + orderEntity.getId() + " succeed");
			target.setUser(userService.getUser(5));
			targetService.addTarget(target);
		}
		updateCustomerOrderNum();
		
		
	}
	
	@RequestMapping (value="/order/addreport", method=RequestMethod.POST)
	@ResponseBody
    public void addOrderReport(@RequestBody Report reportEntity ) throws UserNotFoundException {
    	logger.info("IN: Report/list-Add order");
    	Report report = new Report();
    	report.setOrderId(reportEntity.getOrderId());
    	report.setDescription(reportEntity.getDescription());
    	report.setSolution(reportEntity.getSolution());
    	orderService.addReport(report);
    	
    	logger.info("reportid = " + report.getId());
    	
    	Target target = new Target();
        //String str = "New progress report for order " + reportEntity.getOrderId() + " for customer " + order.getCustomer().getCustomername();
        target.setDetails("Report "+ report.getId() +" : " + reportEntity.getDescription());
        target.setStart(new Date());
        target.setTitle("order Id "+reportEntity.getOrderId() + " has new progress report");
        target.setUser(userService.getUser(5)); // 5 is for manager
        
        targetService.addTarget(target);    
    }
	
	@RequestMapping (value="/order/viewreport", method=RequestMethod.GET)
	@ResponseBody
    public List<Report> viewOrderReport(@RequestParam(value="orderId") int orderId ) {
    	logger.info("IN: Report/list-view report");
    	List<Report> reports = orderService.getReports();
    	List<Report> currentreports = Lists.newArrayList();
    	
    	for(Report report : reports) {
    		if(report.getOrderId() == orderId) {
    			currentreports.add(report);
    		} 			
    	}
    	
    	return currentreports;
    }
	
	
	
	@RequestMapping (value="/ordernew/list", method=RequestMethod.GET)
	@ResponseBody
	public List<OrderFetchFullEntity> ListofNewOrders() throws ComputerNotFoundException {
		
		logger.info("IN: Order/list-GET-New");
		logger.info(orderService.getOrderFetchEntities(false).toString());

		List<OrderFetchEntity> orderFetchEntities = orderService.getOrderFetchEntities(false);
		//***************************add computer name to the computerlist **********************//
		List<OrderFetchFullEntity> orderFetchFullEntities = Lists.newArrayList();
		for(OrderFetchEntity orderFetchEntity : orderFetchEntities) {
			OrderFetchFullEntity orderFetchFullEntity = new OrderFetchFullEntity();
			
			orderFetchFullEntity.setBuilddate(orderFetchEntity.getBuilddate());
			orderFetchFullEntity.setCustomer(orderFetchEntity.getCustomer());
			orderFetchFullEntity.setId(orderFetchEntity.getId());
			orderFetchFullEntity.setPrice(orderFetchEntity.getPrice());
			orderFetchFullEntity.setReason(orderFetchEntity.getReason());
			orderFetchFullEntity.setStatus(orderFetchEntity.getStatus());
			orderFetchFullEntity.setUser(orderFetchEntity.getUser());
			
			if (orderFetchEntity.getComputerlist().size() > 0) {
				List<ComputernumberEntity> computernumberEntities = Lists.newArrayList();
				for (Computernumber computernum : orderFetchEntity
						.getComputerlist()) {

					ComputernumberEntity computernumberEntity = new ComputernumberEntity();
					computernumberEntity.setComputername(computerService
							.getComputer(computernum.getComputerId())
							.getComputername());
					computernumberEntity.setComputerId(computernum
							.getComputerId());
					computernumberEntity.setNumber(computernum.getNumber());
					computernumberEntity.setId(computernum.getId());

					computernumberEntities.add(
							computernumberEntity);
				}
				orderFetchFullEntity.setComputerlist(computernumberEntities);
			}
			orderFetchFullEntities.add(orderFetchFullEntity);
		}
		return orderFetchFullEntities;
	}
	
	@RequestMapping (value="/order/list", method=RequestMethod.GET)
	@ResponseBody
	public List<OrderFetchFullEntity> ListofApprovalOrders(@RequestParam(value="typeId") String str) throws ComputerNotFoundException {
		
		logger.info("IN: Order/list-GET-Approval");

		List<OrderFetchEntity> orderFetchEntities = orderService
				.getOrderFetchEntities(true);
		if(str.length() > 0){
			orderFetchEntities = orderService.getOrderSearchEntities(str);
		}

		// ***************************add computer name to the computerlist
		// **********************//
		List<OrderFetchFullEntity> orderFetchFullEntities = Lists.newArrayList();
				
		for (OrderFetchEntity orderFetchEntity : orderFetchEntities) {
			OrderFetchFullEntity orderFetchFullEntity = new OrderFetchFullEntity();

			orderFetchFullEntity.setBuilddate(orderFetchEntity.getBuilddate());
			orderFetchFullEntity.setCustomer(orderFetchEntity.getCustomer());
			orderFetchFullEntity.setId(orderFetchEntity.getId());
			orderFetchFullEntity.setPrice(orderFetchEntity.getPrice());
			orderFetchFullEntity.setReason(orderFetchEntity.getReason());
			orderFetchFullEntity.setStatus(orderFetchEntity.getStatus());
			orderFetchFullEntity.setUser(orderFetchEntity.getUser());

			if (orderFetchEntity.getComputerlist().size() > 0) {
				List<ComputernumberEntity> computernumberEntities = Lists
						.newArrayList();
				for (Computernumber computernum : orderFetchEntity
						.getComputerlist()) {

					ComputernumberEntity computernumberEntity = new ComputernumberEntity();
					computernumberEntity.setComputername(computerService
							.getComputer(computernum.getComputerId())
							.getComputername());
					computernumberEntity.setComputerId(computernum
							.getComputerId());
					computernumberEntity.setNumber(computernum.getNumber());
					computernumberEntity.setId(computernum.getId());

					computernumberEntities.add(computernumberEntity);
				}
				orderFetchFullEntity.setComputerlist(computernumberEntities);
			}
			orderFetchFullEntities.add(orderFetchFullEntity);
		}
		
		List<OrderFetchFullEntity> oneFullEntity = Lists.newArrayList();
		
		if (getOrderId() != 0) {
			for (OrderFetchFullEntity orderFetchEntity : orderFetchFullEntities) {
				if (orderFetchEntity.getId() == getOrderId()) {
					oneFullEntity.add(orderFetchEntity);
				}
			}
			setOrderId(0);
			return oneFullEntity;
		}
		else
			return orderFetchFullEntities;
	}
	
	
    @RequestMapping(value = "/order")
    //@PreAuthorize("hasRole('CTRL_USER_LIST_GET')")
	public String ordermanagement(@RequestParam(value="orderId") int base) {
    	logger.info("value=" + base);
    	setOrderId(base);
		return "order-management";
	}
    
    void setOrderId(int value) {
    	orderId = value;
    }
    
    int getOrderId() {
    	return orderId;
    }
    
	@RequestMapping (value="/order/search", method=RequestMethod.GET)
	@ResponseBody
	public OrdertimeNumber getSearchResult(@RequestParam(value="typeId") String str){
		OrdertimeEntity example =  orderService.getSearchOrder(str);
		OrdertimeNumber res = new OrdertimeNumber();
		res.setLoadTime(example.getLoadTime());
		res.setTotalNum(example.getTotalNum());
		return res;
	}
    
    void updateCustomerOrderNum() {
    	List<Order> orders = orderService.getOrders();
    	List<Customer> customers = customerService.getCustomers();
    	for(Customer customer : customers) {
    		int customerId = customer.getId();
    		int orderNum = 0;
    		for(Order order : orders) {
    			if(order.getCustomer().getId() == customerId)
    				orderNum++;
    		}
    		customer.setOrdernumber(orderNum);
    		try {
				customerService.updateCustomer(customer);
			} catch (CustomerNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DuplicateCustomerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    
    
    @RequestMapping(value = "/neworder")
    //@PreAuthorize("hasRole('CTRL_USER_LIST_GET')")
	public String ordernewmanagement() {
		return "order-new";
	}
    
    @RequestMapping(value = "/approvalorder")
    //@PreAuthorize("hasRole('CTRL_USER_LIST_GET')")
	public String approvalmanagement() {
		return "approval-management";
	}
    
}
