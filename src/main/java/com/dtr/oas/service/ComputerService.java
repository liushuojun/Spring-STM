package com.dtr.oas.service;
import java.util.List;




import com.dtr.oas.model.Computer;
import com.dtr.oas.exception.DuplicateComputerException;
import com.dtr.oas.exception.ComputerNotFoundException;
 
public interface ComputerService {
 
    public void addComputer(Computer computer) throws DuplicateComputerException;
 
    public Computer getComputer(int computerId) throws ComputerNotFoundException;
 
    public Computer getComputer(String computername,String series) throws ComputerNotFoundException;
 
    public void updateComputer(Computer computer) throws ComputerNotFoundException, DuplicateComputerException;
 
    public void deleteComputer(int computerId) throws ComputerNotFoundException;
 
    public List<Computer> getComputers();
    
}
