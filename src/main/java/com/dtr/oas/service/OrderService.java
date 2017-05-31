package com.dtr.oas.service;
import java.util.List;

import com.dtr.oas.model.Order;
import com.dtr.oas.model.OrderFetchEntity;
import com.dtr.oas.model.OrdertimeEntity;
import com.dtr.oas.model.Report;
import com.dtr.oas.model.Status;
import com.dtr.oas.model.User;
 
public interface OrderService {
 
    public void addOrder(Order order) ;
 
    public Order getOrder(int orderId) ;
 
    public void updateOrder(Order order);
 
    public void deleteOrder(int orderId) ;
 
    public List<Order> getOrders();
    
    public List<Status> getTypes();
    
    public List<User> getUsers();

	List<OrderFetchEntity> getOrderFetchEntities(boolean b);
	
	public Status getStatus(int statusId);

	public void addReport(Report report);

	public List<Report> getReports();

	public Report getReport(int reportId);

	public void updateReport(Report report);
	
	public OrdertimeEntity getSearchOrder(String str);
	
	public List<OrderFetchEntity> getOrderSearchEntities(String str);
}
