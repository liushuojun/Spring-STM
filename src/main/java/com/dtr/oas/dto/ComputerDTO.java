package com.dtr.oas.dto;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.common.base.Objects;
 
public class ComputerDTO implements Serializable {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = -1266162111126935346L;

    static Logger logger = LoggerFactory.getLogger(ComputerDTO.class);
     
    private int id;
     
    @NotNull(message = "{error.computer.computername.null}")
    @NotEmpty(message = "{error.computer.computername.empty}")
    @Size(max = 50, message = "{error.computer.computername.max}")
    private String computername;
 
    @NotNull(message = "{error.computer.series.null}")
    @NotEmpty(message = "{error.computer.series.empty}")
    @Size(max = 50, message = "{error.computer.series.max}")
    private String series;
     
    private int processorId;
    
    private int systemId;
    
    private int memoryId;
    
    private int displayId;
    
    private int price;
     
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getComputername() {
        return computername;
    }
    public void setComputername(String username) {
        this.computername = username;
    }
    public String getSeries() {
        return series;
    }
    public void setSeries(String Series) {
        this.series = Series;
    }

    public int getProcessorId() {
        return processorId;
    }
    
    public void setProcessorId(int processorId) {
        this.processorId = processorId;
    }
    
    public int getSystemId() {
    	return systemId;
    }
    
    public void setSystemId(int systemId) {
    	this.systemId = systemId;
    }
    
    public int getMemoryId() {
    	return memoryId;
    }
    
    public void setMemoryId(int memoryId) {
    	this.memoryId = memoryId;
    }
    
    public int getDisplayId() {
    	return displayId;
    }
    
    public void setDisplayId(int displayId) {
    	this.displayId = displayId;
    }
    
    public int getPrice() {
    	return price;
    }
    
    public void setPrice(int price) {
    	this.price = price;
    }
     
    @Override
    public String toString() {
        return String.format("%s(id=%d, computername=%s, series=%s, processID=%d, systemID=%d, memoryId=%d, displayId=%d, price=%d)", 
                this.getClass().getSimpleName(), 
                this.getId(), 
                this.getComputername(), 
                this.getSeries(), 
                this.getProcessorId(),
                this.getSystemId(),
                this.getMemoryId(),
                this.getDisplayId(),
                this.getPrice());
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
 
        if (o instanceof ComputerDTO) {
            final ComputerDTO other = (ComputerDTO) o;
            return Objects.equal(getId(), other.getId())
                    && Objects.equal(getComputername(), other.getComputername())
                    && Objects.equal(getSeries(), other.getSeries())
                    && Objects.equal(getProcessorId(), other.getProcessorId())
                    && Objects.equal(getSystemId(), other.getSystemId())
                    && Objects.equal(getMemoryId(), other.getMemoryId())
                    && Objects.equal(getDisplayId(), other.getDisplayId())
                    && Objects.equal(getPrice(), other.getPrice());
        }
        return false;
    }
 
    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getComputername(), getSeries(), getProcessorId(),getSystemId(),getMemoryId(),getDisplayId());
    }
}