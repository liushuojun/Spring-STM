package com.dtr.oas.controller;
import java.util.Collection;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dtr.oas.model.User;
import com.dtr.oas.service.UserService;
 
@Controller
public class LinkController {
    static Logger logger = LoggerFactory.getLogger(LinkController.class);
    
    @Autowired
    private static  UserService userService;
 
    @RequestMapping(value = "/")
    public String mainPage() {
        Collection<GrantedAuthority> authorities = getAuthorities();
        //String rolename;
        
        logger.info(authorities.toString());
/*        for (GrantedAuthority authority : authorities) {
            rolename = authority.getAuthority();
             
            if (rolename.equals("ROLE_ADMIN")) {
                logger.debug("Directing to home page for: [" + rolename + "]");
                return "home-admin";
            }
            if (rolename.equals("ROLE_TRADER")) {
                logger.debug("Directing to home page for: [" + rolename + "]");
                return "home-admin";
            }
            if (rolename.equals("ROLE_USER")) {
                logger.debug("Directing to home page for: [" + rolename + "]");
                return "home-admin";
            }
        }*/
         
        logger.error("Role not found - directing to home page for ROLE_USER");
        return "personal-calendar";
    }
 
    @RequestMapping(value = "/index")
    public String indexPage() {
        return "redirect:/";
    	//return "calendar";
    }
     
    private Collection<GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info(principal.toString());
        if (principal instanceof User) {
            authorities = ((User) principal).getAuthorities();
        } else {
            logger.error("Principal is not an instance of com.dtr.oas.model.User");
        }
        return authorities;
    }
    
 
	public static int getCurrentUserId() {
		int userId = 0;
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		logger.info(principal.toString());
		if (principal instanceof User) {
			if (((User) principal).getId() == null) {
				String name = ((User) principal).getUsername().toString();
				if (name.equals("Salestaff-1"))
					userId = 6;
				else if (name.equals("Salestaff-2"))
					userId = 7;
				else if (name.equals("Salestaff-3"))
					userId = 8;
				else if (name.equals("servicestaff"))
					userId = 3;
				else if (name.equals("manager"))
					userId = 5;
			} else
				userId = ((User) principal).getId();
			
		}
		return userId;
	}
}

