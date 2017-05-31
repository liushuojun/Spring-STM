package com.dtr.oas.dao;
import java.util.List;

import com.dtr.oas.exception.ComputerPartsNotFoundException;
import com.dtr.oas.exception.DuplicateComputerPartsException;
import com.dtr.oas.model.Memory;
 
public interface MemoryDAO {
 
    public void addMemory(Memory memory) throws DuplicateComputerPartsException;
 
    public Memory getMemory(int id) throws ComputerPartsNotFoundException;
 
    public Memory getMemory(String memorytype) throws ComputerPartsNotFoundException;
 
    public void updateMemory(Memory memory) throws ComputerPartsNotFoundException;
 
    public void deleteMemory(int id) throws ComputerPartsNotFoundException;
 
    public List<Memory> getMemorys();
}