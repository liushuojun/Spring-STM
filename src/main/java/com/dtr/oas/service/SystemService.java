package com.dtr.oas.service;
import java.util.List;

import com.dtr.oas.exception.DuplicateComputerPartsException;
import com.dtr.oas.exception.ComputerPartsNotFoundException;
import com.dtr.oas.model.System;
 
public interface SystemService {
 
    public void addSystem(System system) throws DuplicateComputerPartsException;
    
    public System getSystem(int id) throws ComputerPartsNotFoundException;
 
    public System getSystem(String systemtype) throws ComputerPartsNotFoundException;
 
    public void updateSystem(System system) throws ComputerPartsNotFoundException;
 
    public void deleteSystem(int id) throws ComputerPartsNotFoundException;
 
    public List<System> getSystems();
 
}