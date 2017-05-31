package com.dtr.oas.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.dtr.oas.model.Strategycreationmodel;
import com.dtr.oas.model.Strategy;
import com.dtr.oas.service.StrategyService;

@Controller
public class StrategyController {
    static Logger logger = LoggerFactory.getLogger(StrategyController.class);

    @Autowired
    private StrategyService strategyService;

    @RequestMapping(value = "/strategy/list", method = RequestMethod.GET)//, headers="Accept=application/json"
    @ResponseBody
    public List<Strategy> listOfStrategies() {
        logger.info("IN: Strategy/list-GET");

        List<Strategy> strategies = strategyService.getStrategies();
        //int number=strategies.size();
       // String str = strategies.toString();
        
        return strategies; 
    }
    
    @RequestMapping(value ="/strategy/strategyadd",method = RequestMethod.POST)
    @ResponseBody
	public void addOffice(@RequestBody Strategy strategy) {
    	strategyService.addStrategy(strategy);
	}
    
	@RequestMapping(value = "/strategy/strategydelete", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteStrategy(@RequestParam int id) {
		// return false if any user in this office or id does not exist
		 strategyService.deleteStrategy(id);
	}
	
	@RequestMapping(value = "/strategy/strategyupdate", method = RequestMethod.POST)
	@ResponseBody
	public void updateUserAccount(@RequestBody Strategy strategy) {
		strategyService.updateStrategy(strategy);
	}
    
    @RequestMapping(value = "/strategy")
	public String officemanagement() {
		return "office-management";
	}


}
