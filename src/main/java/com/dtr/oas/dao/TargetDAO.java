package com.dtr.oas.dao;
import java.util.List;

import com.dtr.oas.model.Target;
 
public interface TargetDAO {
 
    public void addTarget(Target Target) ;
 
    public Target getTarget(int targetId) ;
 
    public void updateTarget(Target target) ;
 
    public void deleteTarget(int targetId) ;
 
    public List<Target> getTargets();
      
}
