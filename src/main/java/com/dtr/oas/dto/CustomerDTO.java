package com.dtr.oas.dto;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.common.base.Objects;
 
public class CustomerDTO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1226330836192961388L;

	static Logger logger = LoggerFactory.getLogger(CustomerDTO.class);
     
    private int id;
     
    @NotNull(message = "{error.customer.customername.null}")
    @NotEmpty(message = "{error.customer.customername.empty}")
    @Size(max = 50, message = "{error.customer.customername.max}")
    private String customername;
 
    private int employeeNum;
     
    private int levelId;
    
    private int industryId;
     
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCustomername() {
        return customername;
    }
    public void setCustomername(String username) {
        this.customername = username;
    }
    public int getEmployeeNum() {
        return employeeNum;
    }
    public void setEmployeeNum(int employeeNum) {
        this.employeeNum = employeeNum;
    }

    public int getLevelId() {
        return levelId;
    }
    
    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }
    
    public int getIndustryId() {
    	return industryId;
    }
    
    public void setIndustryId(int industryId) {
    	this.industryId = industryId;
    }
    
    /*
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
     */
    @Override
    public String toString() {
        return String.format("%s(id=%d, customername=%s, employeeNum=%s, LevelId=%d, IndustryId=%d)", 
                this.getClass().getSimpleName(), 
                this.getId(), 
                this.getCustomername(), 
                this.getEmployeeNum(), 
                this.getLevelId(),
                this.getIndustryId());
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
 
        if (o instanceof CustomerDTO) {
            final CustomerDTO other = (CustomerDTO) o;
            return Objects.equal(getId(), other.getId())
                    && Objects.equal(getCustomername(), other.getCustomername())
                    && Objects.equal(getEmployeeNum(), other.getEmployeeNum())
                    && Objects.equal(getLevelId(), other.getLevelId())
                    && Objects.equal(getIndustryId(), other.getIndustryId());
        }
        return false;
    }
 
    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getCustomername(), getEmployeeNum(), getLevelId(),getIndustryId());
    }
}
