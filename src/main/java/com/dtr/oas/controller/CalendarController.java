package com.dtr.oas.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dtr.oas.exception.UserNotFoundException;
import com.dtr.oas.model.Report;
import com.dtr.oas.model.Target;
import com.dtr.oas.model.TargetEntity;
import com.dtr.oas.model.TargetFetchEntity;
import com.dtr.oas.model.User;
import com.dtr.oas.model.UserFetchEntity;
import com.dtr.oas.service.OrderService;
import com.dtr.oas.service.TargetService;
import com.dtr.oas.service.UserService;
import com.google.common.collect.Lists;

@Controller
public class CalendarController {
    static Logger logger = LoggerFactory.getLogger(CalendarController.class);

    @Autowired
    private TargetService targetService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private OrderService orderService;
	
	@RequestMapping (value="/calendar/events", method=RequestMethod.GET)
	@ResponseBody
	public List<TargetFetchEntity> ListofEvents() {
		
		logger.info("IN: Events/list-GET");
		logger.info(targetService.getTargetFetchEntities().toString());

		return targetService.getTargetFetchEntities();
	}
	
	@RequestMapping (value="/calendar/updateevent", method=RequestMethod.POST)
	@ResponseBody
	public void UpdateEvents(@RequestBody TargetEntity targetEntity) throws UserNotFoundException {
		
		logger.info("IN: Events/update-GET");
		logger.info(targetEntity.toString());
		
		Target target = new Target();
		
		target.setDetails(targetEntity.getDetails());
		target.setEnd(targetEntity.getEnd());
		target.setStart(targetEntity.getStart());
		target.setTitle(targetEntity.getTitle());
		target.setFeedback(targetEntity.getFeedback());
		if(targetEntity.getUserId() != 0){
			target.setUser(userService.getUser(targetEntity.getUserId()));//for edit using formdata
		} else if(targetEntity.getUser() != null)
		{
			target.setUser(userService.getUser(targetEntity.getUser()));// for edit using drag and drop
		} else
		{
			target.setUser(userService.getUser(LinkController.getCurrentUserId()));
		}
			
		
		
		target.setId(targetEntity.getId());

		targetService.updateTarget(target);
		
		if(targetEntity.getTitle().contains("progress report")) {
			//int reportId = Integer.parseInt(targetEntity.getDetails().substring(7, 8));
			String str = targetEntity.getDetails();
			str = str.replaceAll("\\D+","");
			int reportId = Integer.parseInt(str);
			logger.info("reportId = "+reportId);
			Report report = orderService.getReport(reportId);
			report.setSolution(targetEntity.getFeedback());
			orderService.updateReport(report);
		}
	}
	
	@RequestMapping (value="/calendar/addevent", method=RequestMethod.POST)
	@ResponseBody
	public void AddEvents(@RequestBody TargetEntity targetEntity) throws UserNotFoundException {
		
		logger.info("IN: Events/update-add");
		logger.info(targetEntity.toString());
		
		int currentUserId = LinkController.getCurrentUserId();
		
		Target target = new Target();
		
		target.setDetails(targetEntity.getDetails());
		target.setEnd(targetEntity.getEnd());
		target.setStart(targetEntity.getStart());
		target.setTitle(targetEntity.getTitle());
		target.setFeedback(targetEntity.getFeedback());
		if(targetEntity.getUserId() != 0) {
			target.setUser(userService.getUser(targetEntity.getUserId()));
		}
		else
		{
			target.setUser(userService.getUser(currentUserId));
		}
		

		targetService.addTarget(target);
	}
	
	@RequestMapping (value="/calendar/users", method=RequestMethod.GET)
	@ResponseBody
    public List<UserFetchEntity> getAllUsers() {
    	logger.info("IN: Order/list-GET Users");
		List<User>  users = userService.getUsers();
        List<UserFetchEntity> userFetchEntities = Lists.newArrayList();
        for(User user: users) {
        	UserFetchEntity userFetchEntity = new UserFetchEntity();
        	userFetchEntity.setId(user.getId());
        	userFetchEntity.setUsername(user.getUsername());
        	
        	userFetchEntities.add(userFetchEntity);
        }
        return userFetchEntities;
    }
	
	
    @RequestMapping(value = "/calendar")
    //@PreAuthorize("hasRole('CTRL_USER_LIST_GET')")
	public String calendarmanagement() throws UserNotFoundException {
    	int currentUserId = LinkController.getCurrentUserId();
    	logger.info("current user id: "+ currentUserId);
		return "personal-calendar";
	}   
}
