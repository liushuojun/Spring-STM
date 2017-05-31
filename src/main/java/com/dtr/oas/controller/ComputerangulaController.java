package com.dtr.oas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ComputerangulaController {
    static Logger logger = LoggerFactory.getLogger(ComputerangulaController.class);
    //static String businessObject = "customer"; //used in RedirectAttributes messages 

   
		
    
    @RequestMapping(value = "/computerlistangular")
	public String computerlistangular() {
    	
		return "computer-list-angular";
	}
    
    
}
