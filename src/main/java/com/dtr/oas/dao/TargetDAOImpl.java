package com.dtr.oas.dao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dtr.oas.model.Customer;
import com.dtr.oas.model.Target;
 
@Repository
public class TargetDAOImpl implements TargetDAO {
	
	static Logger logger = LoggerFactory.getLogger(TargetDAOImpl.class);
	 
    @Autowired
    private SessionFactory sessionFactory;
 
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
	@Override
	public void addTarget(Target target) {
		getCurrentSession().save(target);

	}

	@Override
	public Target getTarget(int targetId) {
        logger.info("TargetDAOImpl.getTarget() - [" + targetId + "]");
        Target targetObject = (Target) getCurrentSession().get(Target.class, targetId);
         
        if (targetObject == null) {
        	logger.info("TargetDAOImpl.getTarget() - [" + targetId + "] have targets");
        	return null;
        } else {
            return targetObject;
        }
	}

	@Override
	public void updateTarget(Target target) {
		
	Target targetCheck = getTarget(target.getId());
		
		if(targetCheck.getId() == target.getId()) {
			targetCheck.setTitle(target.getTitle());
			targetCheck.setDetails(target.getDetails());
			targetCheck.setEnd(target.getEnd());
			targetCheck.setStart(target.getStart());
			targetCheck.setFeedback(target.getFeedback());
			targetCheck.setUser(target.getUser());
			getCurrentSession().update(targetCheck);
		}
		else
			logger.info(target.toString());

	}

	@Override
	public void deleteTarget(int targetId) {
		Target target = getTarget(targetId);
		
		if(target != null)
			getCurrentSession().delete(target);	

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Target> getTargets() {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("from Target").list();
	}


}
