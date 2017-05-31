package com.dtr.oas.dao;
import java.util.List;

import com.dtr.oas.model.Order;
import com.dtr.oas.model.OrdertimeEntity;
import com.dtr.oas.model.Report;
import com.dtr.oas.model.Status;
import com.dtr.oas.model.User;
 
public interface OrderDAO {
 
    public void addOrder(Order order) ;
 
    public Order getOrder(int orderId) ;
 
    public void updateOrder(Order order) ;
 
    public void deleteOrder(int orderId) ;
 
    public List<Order> getOrders();
    
    public List<Status> getStatuses();
    
    public List<User> getUsers();

	public Status getStatus(int statusId);

	public void addReport(Report report);

	public List<Report> getReports();

	public Report getReport(int reportId);

	public void updateReport(Report report);
	
	public OrdertimeEntity getSearchOrder(String str);
    
}
