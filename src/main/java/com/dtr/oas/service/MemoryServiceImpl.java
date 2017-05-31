package com.dtr.oas.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dtr.oas.dao.MemoryDAO;
import com.dtr.oas.exception.DuplicateComputerPartsException;
import com.dtr.oas.exception.ComputerPartsNotFoundException;
import com.dtr.oas.model.Memory;

@Service
@Transactional
public class MemoryServiceImpl implements MemoryService {
    static Logger logger = LoggerFactory.getLogger(MemoryServiceImpl.class);
    
    @Autowired
    private MemoryDAO memoryDAO;
 
    @Override
    public void addMemory(Memory memory) throws DuplicateComputerPartsException {
    	memoryDAO.addMemory(memory);
    }
 
    @Override
    public Memory getMemory(int id) throws ComputerPartsNotFoundException {
        return memoryDAO.getMemory(id);
    }
 
    @Override
    public Memory getMemory(String memorytype) throws ComputerPartsNotFoundException {
        return memoryDAO.getMemory(memorytype);
    }
 
    @Override
    public void updateMemory(Memory memory) throws ComputerPartsNotFoundException {
    	memoryDAO.updateMemory(memory);
    }
 
    @Override
    public void deleteMemory(int id) throws ComputerPartsNotFoundException {
    	memoryDAO.deleteMemory(id);
    }
 
    @Override
    public List<Memory> getMemorys() {
        return memoryDAO.getMemorys();
    }

}
