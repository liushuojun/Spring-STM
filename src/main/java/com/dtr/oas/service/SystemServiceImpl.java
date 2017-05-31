package com.dtr.oas.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dtr.oas.dao.SystemDAO;
import com.dtr.oas.exception.DuplicateComputerPartsException;
import com.dtr.oas.exception.ComputerPartsNotFoundException;
import com.dtr.oas.model.System;

@Service
@Transactional
public class SystemServiceImpl implements SystemService {
    static Logger logger = LoggerFactory.getLogger(SystemServiceImpl.class);
    
    @Autowired
    private SystemDAO systemDAO;
 
    @Override
    public void addSystem(System system) throws DuplicateComputerPartsException {
    	systemDAO.addSystem(system);
    }
 
    @Override
    public System getSystem(int id) throws ComputerPartsNotFoundException {
        return systemDAO.getSystem(id);
    }
 
    @Override
    public System getSystem(String systemtype) throws ComputerPartsNotFoundException {
        return systemDAO.getSystem(systemtype);
    }
 
    @Override
    public void updateSystem(System system) throws ComputerPartsNotFoundException {
    	systemDAO.updateSystem(system);
    }
 
    @Override
    public void deleteSystem(int id) throws ComputerPartsNotFoundException {
    	systemDAO.deleteSystem(id);
    }
 
    @Override
    public List<System> getSystems() {
        return systemDAO.getSystems();
    }

}
