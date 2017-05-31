package com.dtr.oas.service;
import java.util.List;

import com.dtr.oas.model.Target;
import com.dtr.oas.model.TargetFetchEntity;
 
public interface TargetService {
 
    public void addTarget(Target target) ;
 
    public Target getTarget(int targetId) ;
 
    public void updateTarget(Target target);
 
    public void deleteTarget(int targetId) ;
 
    public List<Target> getTargets();

	public List<TargetFetchEntity> getTargetFetchEntities();
      
}