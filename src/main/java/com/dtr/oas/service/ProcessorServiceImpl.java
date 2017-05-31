package com.dtr.oas.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dtr.oas.dao.ProcessorDAO;
import com.dtr.oas.exception.DuplicateComputerPartsException;
import com.dtr.oas.exception.ComputerPartsNotFoundException;
import com.dtr.oas.model.Processor;

@Service
@Transactional
public class ProcessorServiceImpl implements ProcessorService {
    static Logger logger = LoggerFactory.getLogger(ProcessorServiceImpl.class);
    
    @Autowired
    private ProcessorDAO processorDAO;
 
    @Override
    public void addProcessor(Processor processor) throws DuplicateComputerPartsException {
    	processorDAO.addProcessor(processor);
    }
 
    @Override
    public Processor getProcessor(int id) throws ComputerPartsNotFoundException {
        return processorDAO.getProcessor(id);
    }
 
    @Override
    public Processor getProcessor(String processortype) throws ComputerPartsNotFoundException {
        return processorDAO.getProcessor(processortype);
    }
 
    @Override
    public void updateProcessor(Processor processor) throws ComputerPartsNotFoundException {
    	processorDAO.updateProcessor(processor);
    }
 
    @Override
    public void deleteProcessor(int id) throws ComputerPartsNotFoundException {
    	processorDAO.deleteProcessor(id);
    }
 
    @Override
    public List<Processor> getProcessors() {
        return processorDAO.getProcessors();
    }

}
