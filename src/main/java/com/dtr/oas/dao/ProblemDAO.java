package com.dtr.oas.dao;
import java.util.List;




import com.dtr.oas.model.Problem;
import com.dtr.oas.model.Type;
 
public interface ProblemDAO {
 
    public void addProblem(Problem problem) ;
 
    public Problem getProblem(int problemId) ;
 
    public void updateProblem(Problem problem) ;
 
    public void deleteProblem(int problemId) ;
 
    public List<Problem> getProblems();
    
    public List<Type> getTypes();

	public Type getType(int typeID);
}