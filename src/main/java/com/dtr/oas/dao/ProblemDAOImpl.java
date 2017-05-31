package com.dtr.oas.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dtr.oas.model.Problem;
import com.dtr.oas.model.Type;

@Repository
public class ProblemDAOImpl implements ProblemDAO{
    static Logger logger = LoggerFactory.getLogger(ProblemDAOImpl.class);
    
    @Autowired
    private SessionFactory sessionFactory;
 
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
	
	@Override
	public void addProblem(Problem problem) {
		getCurrentSession().save(problem);
		
	}

	@Override
	public Problem getProblem(int problemId) {
        logger.info("ProblemDAOImpl.getProblem() - [" + problemId + "]");
        Problem problemObject = (Problem) getCurrentSession().get(Problem.class, problemId);
         
            return problemObject;
	}

	@Override
	public void updateProblem(Problem problem) {
		Problem problemCheck = getProblem(problem.getId());
		
		if(problemCheck.getId() == problem.getId()) {
			problemCheck.setType(problem.getType());
			problemCheck.setDescription(problem.getDescription());
			problemCheck.setSolution(problem.getSolution());
			getCurrentSession().update(problemCheck);
		}
		else
			logger.info(problem.toString());
		
	}

	@Override
	public void deleteProblem(int problemId) {
		Problem problem = getProblem(problemId);
		
		if(problem != null)
			getCurrentSession().delete(problem);	
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Problem> getProblems() {
		return getCurrentSession().createQuery("from Problem").list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Type> getTypes() {
		return getCurrentSession().createQuery("from Type").list();
	}

	@Override
	public Type getType(int typeID) {
		Type typeObject = (Type) getCurrentSession().get(Type.class, typeID);
		return typeObject;
	}

}
