package com.dtr.oas.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.dtr.oas.model.Problem;
import com.dtr.oas.model.ProblemEntity;
import com.dtr.oas.model.ProblemFetchEntity;
import com.dtr.oas.model.Type;
import com.dtr.oas.service.ProblemService;

@Controller
public class ProblemController {
    static Logger logger = LoggerFactory.getLogger(ProblemController.class);
    //static String businessObject = "problem"; //used in RedirectAttributes messages 

    @Autowired
    private ProblemService problemService;


	@RequestMapping (value="/problem/types", method=RequestMethod.GET)
	@ResponseBody
    //@PreAuthorize("hasAnyRole('CTRL_USER_LIST_GET','CTRL_USER_EDIT_GET')")
    public List<Type> getAllProcessors() {
    	logger.info("IN: Problem/list-GETType");
        return problemService.getTypes();
    }
	
    
	@RequestMapping (value="/problem/list", method=RequestMethod.GET)
	@ResponseBody
	public List<ProblemFetchEntity> ListofProblems(@RequestParam(value="typeId") int base) {
		
		logger.info("IN: Problem/list-GET typeId ="+ base);
		

		return problemService.getProblemFetchEntities(base);
	}
	
	@RequestMapping (value="/problem/addproblem", method=RequestMethod.POST)
	@ResponseBody
	public void AddnewProblem(@RequestBody ProblemEntity problemEntity) {
		logger.info("IN: Problem/ADD");
        Problem problem =new Problem();
        problem.setDescription(problemEntity.getDescription());
        problem.setSolution(problemEntity.getSolution());
        problem.setType(problemService.getType(problemEntity.getTypeId()));
        logger.info(problem.toString());
		problemService.addProblem(problem);
	}
	
	@RequestMapping (value="/problem/updateproblem", method=RequestMethod.POST)
	@ResponseBody
	public void UpdateProblem(@RequestBody ProblemEntity problemEntity) {
		logger.info("IN: Problem/update");
        Problem problem =new Problem();
        problem.setId(problemEntity.getId());
        problem.setDescription(problemEntity.getDescription());
        problem.setSolution(problemEntity.getSolution());
        problem.setType(problemService.getType(problemEntity.getTypeId()));
        logger.info(problem.toString());
		problemService.updateProblem(problem);
	}
	
	@RequestMapping (value="/problem/delete", method=RequestMethod.DELETE)
	@ResponseBody
	public void DeleteProblem(@RequestParam int id) {
		logger.info("IN: Problem/delete");
		problemService.deleteProblem(id);
	}
	
	
    
    @RequestMapping(value = "/problem")
    //@PreAuthorize("hasRole('CTRL_USER_LIST_GET')")
	public String problemmanagement() {
		return "problem-management";
	}   
}
