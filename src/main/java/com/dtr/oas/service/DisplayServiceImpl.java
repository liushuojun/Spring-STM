package com.dtr.oas.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dtr.oas.dao.DisplayDAO;
import com.dtr.oas.exception.DuplicateComputerPartsException;
import com.dtr.oas.exception.ComputerPartsNotFoundException;
import com.dtr.oas.model.Display;

@Service
@Transactional
public class DisplayServiceImpl implements DisplayService {
    static Logger logger = LoggerFactory.getLogger(DisplayServiceImpl.class);
    
    @Autowired
    private DisplayDAO displayDAO;
 
    @Override
    public void addDisplay(Display display) throws DuplicateComputerPartsException {
    	displayDAO.addDisplay(display);
    }
 
    @Override
    public Display getDisplay(int id) throws ComputerPartsNotFoundException {
        return displayDAO.getDisplay(id);
    }
 
    @Override
    public Display getDisplay(String displaytype) throws ComputerPartsNotFoundException {
        return displayDAO.getDisplay(displaytype);
    }
 
    @Override
    public void updateDisplay(Display display) throws ComputerPartsNotFoundException {
    	displayDAO.updateDisplay(display);
    }
 
    @Override
    public void deleteDisplay(int id) throws ComputerPartsNotFoundException {
    	displayDAO.deleteDisplay(id);
    }
 
    @Override
    public List<Display> getDisplays() {
        return displayDAO.getDisplays();
    }

}
