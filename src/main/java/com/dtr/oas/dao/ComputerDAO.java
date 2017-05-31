package com.dtr.oas.dao;
import java.util.List;

import com.dtr.oas.exception.DuplicateComputerException;
import com.dtr.oas.exception.ComputerNotFoundException;
import com.dtr.oas.model.Computer;;
 
public interface ComputerDAO {
 
    public void addComputer(Computer Computer) throws DuplicateComputerException;
 
    public Computer getComputer(int computerId) throws ComputerNotFoundException;
 
    public Computer getComputer(String computername,String series) throws ComputerNotFoundException;
 
    public void updateComputer(Computer computer) throws ComputerNotFoundException, DuplicateComputerException;
 
    public void deleteComputer(int computerId) throws ComputerNotFoundException;
 
    public List<Computer> getComputers();
}
