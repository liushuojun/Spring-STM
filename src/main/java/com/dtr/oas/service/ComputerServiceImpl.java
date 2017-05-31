package com.dtr.oas.service;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dtr.oas.dao.ComputerDAO;
import com.dtr.oas.exception.DuplicateComputerException;
import com.dtr.oas.exception.ComputerNotFoundException;
import com.dtr.oas.model.Computer;
 
@Service
@Transactional
public class ComputerServiceImpl implements ComputerService {
    static Logger logger = LoggerFactory.getLogger(ComputerServiceImpl.class);
     
    @Autowired
    private ComputerDAO computerDAO;
 
    @Override
    public void addComputer(Computer computer) throws DuplicateComputerException {
    	computerDAO.addComputer(computer);;
    }
 
    @Override
    public Computer getComputer(int computerId) throws ComputerNotFoundException {
        return computerDAO.getComputer(computerId);
    }
 
    @Override
    public Computer getComputer(String computername,String series) throws ComputerNotFoundException {
        return computerDAO.getComputer(computername,series);
    }
 
    @Override
    public void updateComputer(Computer computer) throws ComputerNotFoundException, DuplicateComputerException {
    	computerDAO.updateComputer(computer);
    }
 
    @Override
    public void deleteComputer(int computerId) throws ComputerNotFoundException {
    	computerDAO.deleteComputer(computerId);
    }
 
    @Override
    public List<Computer> getComputers() {
        return computerDAO.getComputers();
    }
    /* 
    //TODO Dummy role added temporarily until next example
    @Override
    public ComputerDetails loadComputerByComputername(String username) throws ComputernameNotFoundException {
        try {
            return getComputer(username);
        } catch (ComputerNotFoundException e) {
            throw new ComputernameNotFoundException(e.getMessage());
        }
    }
    */
}
