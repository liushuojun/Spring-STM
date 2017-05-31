package com.dtr.oas.service;
import java.util.List;

import com.dtr.oas.model.Problem;
import com.dtr.oas.model.ProblemFetchEntity;
import com.dtr.oas.model.Type;
 
public interface ProblemService {
 
    public void addProblem(Problem problem) ;
 
    public Problem getProblem(int problemId) ;
 
    public void updateProblem(Problem problem);
 
    public void deleteProblem(int problemId) ;
 
    public List<Problem> getProblems();
    
    public List<Type> getTypes();

	public List<ProblemFetchEntity> getProblemFetchEntities(int typeId);
	
	public Type getType(int typeID);
    
}