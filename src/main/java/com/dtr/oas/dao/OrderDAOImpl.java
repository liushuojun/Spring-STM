package com.dtr.oas.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dtr.oas.model.Computer;
import com.dtr.oas.model.Order;
import com.dtr.oas.model.OrdertimeEntity;
import com.dtr.oas.model.Report;
import com.dtr.oas.model.Status;
import com.dtr.oas.model.User;

@Repository
public class OrderDAOImpl implements OrderDAO {
    static Logger logger = LoggerFactory.getLogger(OrderDAOImpl.class);
    
    @Autowired
    private SessionFactory sessionFactory;
 
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
	@Override
	public void addOrder(Order order) {
		 logger.info("OrderDAOImpl.addOrder() - [" + order.getId() + "]");
		 getCurrentSession().save(order);

	}

	@Override
	public Order getOrder(int orderId) {
		Order orderObject = (Order) getCurrentSession().get(Order.class, orderId);
        if (orderObject == null ) {
            logger.info("Order id [" + orderId + "] not found");
            return null;
        } else {
            return orderObject;
        }
    }

	@Override
	public void updateOrder(Order order) {
		logger.info(order.toString());
		getCurrentSession().update(order);

	}

	@Override
	public void deleteOrder(int orderId) {
		Order order = getOrder(orderId);
		
		if(order != null)
			getCurrentSession().delete(order);	

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Order> getOrders() {
/*	    List<Order> res = new ArrayList<Order>();
		Query qry = getCurrentSession().createQuery("from Order where status.id <= :statusId");
		qry.setParameter("statusId", 2);*/
		
		
		Query qry_approved = getCurrentSession().createQuery("from Order where id > :idValue");
		qry_approved.setParameter("idValue", getMaxId() - 20);
		return qry_approved.list();
	}
	
	public int getMaxId(){
		int res = 0;
		Query qry = getCurrentSession().createQuery("select Max(id) from Order where status.id > :statusId");
		qry.setParameter("statusId", 2);
		res = (int) qry.list().get(0);
		return res;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Status> getStatuses() {
		
		return getCurrentSession().createQuery("from Status").list();
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		
	    return getCurrentSession().createQuery("from User").list();
	}
	@Override
	public Status getStatus(int statusId) {
		Status statusObject = (Status) getCurrentSession().get(Status.class, statusId);
        if (statusObject == null ) {
            logger.info("Status id [" + statusId + "] not found");
            return null;
        } else {
            return statusObject;
        }
	}
	@Override
	public void addReport(Report report) {
		 logger.info("OrderDAOImpl.addReport() - [" + report.getId() + "]");
		 getCurrentSession().save(report);
		
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<Report> getReports() {
		
		return getCurrentSession().createQuery("from Report").list();
	}
	
	@Override
	public Report getReport(int reportId) {
		Report reportObject = (Report) getCurrentSession().get(Report.class, reportId);
        if (reportObject == null ) {
            logger.info("Order id [" + reportId + "] not found");
            return null;
        } else {
            return reportObject;
        } 
	}
	@Override
	public void updateReport(Report report) {
		getCurrentSession().update(report);
		
	}
	@Override
	public OrdertimeEntity getSearchOrder(String str) {
		// TODO Auto-generated method stub
		OrdertimeEntity res = new OrdertimeEntity();
        Query query = getCurrentSession().createQuery("from Order where reason like :searchStr");
        int totalNumber = ((Long)getCurrentSession().createQuery("select count(*) from Order").uniqueResult()).intValue();
        query.setString("searchStr", str + "%");
        long before = System.currentTimeMillis();
		List<Order> list = (List<Order>)query.list();
		long after = System.currentTimeMillis();
		res.setLoadTime(after - before);
		res.setOrderList(list);
		res.setTotalNum(totalNumber);
		return res;
	}

}
