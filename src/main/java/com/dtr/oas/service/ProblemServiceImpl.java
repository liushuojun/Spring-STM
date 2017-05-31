package com.dtr.oas.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dtr.oas.dao.ProblemDAO;
import com.dtr.oas.model.Problem;
import com.dtr.oas.model.ProblemFetchEntity;
import com.dtr.oas.model.Type;
import com.google.common.collect.Lists;

@Service
@Transactional
public class ProblemServiceImpl implements ProblemService {
	
    static Logger logger = LoggerFactory.getLogger(ProblemServiceImpl.class);
    
    @Autowired
    private ProblemDAO problemDAO;

	@Override
	public void addProblem(Problem problem) {
		problemDAO.addProblem(problem);

	}

	@Override
	public Problem getProblem(int problemId) {
		return problemDAO.getProblem(problemId);
	}

	@Override
	public void updateProblem(Problem problem) {
		problemDAO.updateProblem(problem);
	}

	@Override
	public void deleteProblem(int problemId) {
		problemDAO.deleteProblem(problemId);

	}

	@Override
	public List<Problem> getProblems() {
		// TODO Auto-generated method stub
		return problemDAO.getProblems();
	}

	@Override
	public List<Type> getTypes() {
		return problemDAO.getTypes();
	}

	@Override
	public List<ProblemFetchEntity> getProblemFetchEntities(int typeId) {
		List<Problem> problems = problemDAO.getProblems();
		List<ProblemFetchEntity> problemFetchEntities = Lists.newArrayList();
		for(Problem problem : problems) {
			ProblemFetchEntity problemFetchEntity = new ProblemFetchEntity();
			problemFetchEntity.setId(problem.getId());
			problemFetchEntity.setType(problem.getType().getName());
			problemFetchEntity.setSolution(problem.getSolution());
			problemFetchEntity.setDescription(problem.getDescription());
			if(typeId == 0 || typeId ==problem.getType().getId()) {
				problemFetchEntities.add(problemFetchEntity);
			}
		}
		return problemFetchEntities;
	}

	@Override
	public Type getType(int typeID) {
		// TODO Auto-generated method stub
		return problemDAO.getType(typeID);
	}
}
