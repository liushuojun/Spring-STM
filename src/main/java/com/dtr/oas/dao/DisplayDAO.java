package com.dtr.oas.dao;
import java.util.List;

import com.dtr.oas.exception.ComputerPartsNotFoundException;
import com.dtr.oas.exception.DuplicateComputerPartsException;
import com.dtr.oas.model.Display;
 
public interface DisplayDAO {
 
    public void addDisplay(Display display) throws DuplicateComputerPartsException;
 
    public Display getDisplay(int id) throws ComputerPartsNotFoundException;
 
    public Display getDisplay(String displaytype) throws ComputerPartsNotFoundException;
 
    public void updateDisplay(Display display) throws ComputerPartsNotFoundException;
 
    public void deleteDisplay(int id) throws ComputerPartsNotFoundException;
 
    public List<Display> getDisplays();
}
