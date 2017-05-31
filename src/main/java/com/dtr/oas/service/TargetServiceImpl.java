package com.dtr.oas.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dtr.oas.controller.LinkController;
import com.dtr.oas.dao.TargetDAO;
import com.dtr.oas.model.Target;
import com.dtr.oas.model.TargetFetchEntity;
import com.google.common.collect.Lists;

@Service
@Transactional
public class TargetServiceImpl implements TargetService {
	
	 static Logger logger = LoggerFactory.getLogger(TargetServiceImpl.class);
	    
	    @Autowired
	    private TargetDAO targetDAO;

	@Override
	public void addTarget(Target target) {
		targetDAO.addTarget(target);

	}

	@Override
	public Target getTarget(int targetId) {
		// TODO Auto-generated method stub
		return targetDAO.getTarget(targetId);
	}

	@Override
	public void updateTarget(Target target) {
		targetDAO.updateTarget(target);
	}

	@Override
	public void deleteTarget(int targetId) {
		targetDAO.deleteTarget(targetId);
	}

	@Override
	public List<Target> getTargets() {
		// TODO Auto-generated method stub
		return targetDAO.getTargets();
	}

	@Override
	public List<TargetFetchEntity> getTargetFetchEntities() {
		List<Target> targets = targetDAO.getTargets();
		List<TargetFetchEntity> targetFetchEntities = Lists.newArrayList();
		int currentUserId = 0;
		currentUserId = LinkController.getCurrentUserId();
		logger.info("currentUserId" + currentUserId);
		for (Target target : targets) {

			if (currentUserId == target.getUser().getId()) {

				TargetFetchEntity targetFetchEntity = new TargetFetchEntity();

				targetFetchEntity.setDetails(target.getDetails());
				targetFetchEntity.setEnd(target.getEnd());
				targetFetchEntity.setId(target.getId());
				targetFetchEntity.setStart(target.getStart());
				targetFetchEntity.setTitle(target.getTitle());
				targetFetchEntity.setFeedback(target.getFeedback());
				targetFetchEntity.setUser(target.getUser().getUsername());

				targetFetchEntities.add(targetFetchEntity);
			}
		}
		return targetFetchEntities;
	}

}
