package com.dtr.oas.service;
import java.util.List;

import com.dtr.oas.exception.DuplicateComputerPartsException;
import com.dtr.oas.exception.ComputerPartsNotFoundException;
import com.dtr.oas.model.Processor;
 
public interface ProcessorService {
 
    public void addProcessor(Processor processor) throws DuplicateComputerPartsException;
    
    public Processor getProcessor(int id) throws ComputerPartsNotFoundException;
 
    public Processor getProcessor(String processortype) throws ComputerPartsNotFoundException;
 
    public void updateProcessor(Processor processor) throws ComputerPartsNotFoundException;
 
    public void deleteProcessor(int id) throws ComputerPartsNotFoundException;
 
    public List<Processor> getProcessors();
 
}
