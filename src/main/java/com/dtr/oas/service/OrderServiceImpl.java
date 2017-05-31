package com.dtr.oas.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dtr.oas.dao.OrderDAO;
import com.dtr.oas.model.Order;
import com.dtr.oas.model.OrderFetchEntity;
import com.dtr.oas.model.OrdertimeEntity;
import com.dtr.oas.model.Report;
import com.dtr.oas.model.Status;
import com.dtr.oas.model.User;
import com.google.common.collect.Lists;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    
    @Autowired
    private OrderDAO orderDAO;
    
    
    
	@Override
	public void addOrder(Order order) {
		// TODO Auto-generated method stub
		orderDAO.addOrder(order);

	}

	@Override
	public Order getOrder(int orderId) {
		// TODO Auto-generated method stub
		return orderDAO.getOrder(orderId);
	}

	@Override
	public void updateOrder(Order order) {
		// TODO Auto-generated method stub
		orderDAO.updateOrder(order);

	}

	@Override
	public void deleteOrder(int orderId) {
		// TODO Auto-generated method stub
		orderDAO.deleteOrder(orderId);

	}

	@Override
	public List<Order> getOrders() {
		// TODO Auto-generated method stub
		return orderDAO.getOrders();
	}

	@Override
	public List<Status> getTypes() {
		// TODO Auto-generated method stub
		return orderDAO.getStatuses();
	}

	@Override
	public List<OrderFetchEntity> getOrderFetchEntities(boolean b) {
		List<Order> orders = orderDAO.getOrders();
		List<OrderFetchEntity> orderFetchEntities_new = Lists.newArrayList();
		List<OrderFetchEntity> orderFetchEntities_approval = Lists.newArrayList();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		for(Order order : orders) {

			OrderFetchEntity orderFetchEntity = new OrderFetchEntity();
			
			orderFetchEntity.setBuilddate(ft.format(order.getBuilddate()));
			orderFetchEntity.setCustomer(order.getCustomer().getCustomername());
			orderFetchEntity.setId(order.getId());
			orderFetchEntity.setReason(order.getReason());
			orderFetchEntity.setComputerlist(order.getComputerlist());
			orderFetchEntity.setPrice(order.getPrice());
			orderFetchEntity.setStatus(order.getStatus().getType());
			
			if (order.getStatus().getId() <= 2 ) {
				orderFetchEntities_new.add(orderFetchEntity);
				
			}
			else {
				orderFetchEntity.setUser(order.getUser().getUsername());
				orderFetchEntities_approval.add(orderFetchEntity);
			}
		}
		if (b) {
		    logger.info("orderFetchEntities_approval" +orderFetchEntities_approval.toString());
		    if(orderFetchEntities_approval.size() > 20)
		    	return orderFetchEntities_approval.subList(0, 20);
		    else
		    	return orderFetchEntities_approval;
		}
		else
			return orderFetchEntities_new;
	}
	
/*	public List<OrderFetchFullEntity> getOrderFetchFullEntities(int customerId) {
		List<Order> orders = orderDAO.getOrders();
		List<OrderFetchFullEntity> orderFetchFullEntities = Lists.newArrayList();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		for(Order order : orders) {
			if(order.getCustomer().getId() == customerId) {
				OrderFetchFullEntity orderFetchFullEntity = new OrderFetchFullEntity();
				
				orderFetchFullEntity.setBuilddate(ft.format(order.getBuilddate()));
				orderFetchFullEntity.setCustomer(order.getCustomer().getCustomername());
				orderFetchFullEntity.setId(order.getId());
				orderFetchFullEntity.setPrice(order.getPrice());
				orderFetchFullEntity.setStatus(order.getStatus().getType());
				orderFetchFullEntity.setUser(order.getUser().getUsername());
				
				for(Computernumber computernumber : order.getComputerlist()) {
					ComputernumberEntity computernumberEntity = new ComputernumberEntity();
					computernumber.get
				}
				
			}
		}
	}*/

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return  orderDAO.getUsers();
	}

	@Override
	public Status getStatus(int statusId) {
		// TODO Auto-generated method stub
		return orderDAO.getStatus(statusId);
	}

	@Override
	public void addReport(Report report) {
		orderDAO.addReport(report);
		
	}

	@Override
	public List<Report> getReports() {
		
		return orderDAO.getReports();
	}

	@Override
	public Report getReport(int reportId) {
		
		return orderDAO.getReport(reportId);
	}

	@Override
	public void updateReport(Report report) {
		orderDAO.updateReport(report);
		
	}

	@Override
	public OrdertimeEntity getSearchOrder(String str) {
		return orderDAO.getSearchOrder(str);
	}

	@Override
	public List<OrderFetchEntity> getOrderSearchEntities(String str) {
		List<Order> orders = orderDAO.getSearchOrder(str).getOrderList();
		List<OrderFetchEntity> orderFetchEntities_new = Lists.newArrayList();
		List<OrderFetchEntity> orderFetchEntities_approval = Lists.newArrayList();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		for (Order order : orders) {

			OrderFetchEntity orderFetchEntity = new OrderFetchEntity();

			orderFetchEntity.setBuilddate(ft.format(order.getBuilddate()));
			orderFetchEntity.setCustomer(order.getCustomer().getCustomername());
			orderFetchEntity.setId(order.getId());
			orderFetchEntity.setReason(order.getReason());
			orderFetchEntity.setComputerlist(order.getComputerlist());
			orderFetchEntity.setPrice(order.getPrice());
			orderFetchEntity.setStatus(order.getStatus().getType());

			if (order.getStatus().getId() <= 2) {
				orderFetchEntities_new.add(orderFetchEntity);

			} else {
				orderFetchEntity.setUser(order.getUser().getUsername());
				orderFetchEntities_approval.add(orderFetchEntity);
			}
		}
		logger.info("orderFetchEntities_approval" + orderFetchEntities_approval.toString());
		return orderFetchEntities_approval;
	}

}
