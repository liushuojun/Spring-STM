package com.dtr.oas.model;
import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Objects;
 
@Entity
@Table(name = "processor")
public class Processor extends BaseEntity implements Serializable {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 3211755610216173218L;
    static Logger logger = LoggerFactory.getLogger(Processor.class);
 
    @NotNull(message = "{error.processor.type.null}")
    @NotEmpty(message = "{error.processor.type.empty}")
    @Size(max = 50, message = "{error.processor.type.max}")
    @Column(name = "type", length = 50)
    private String type;
     
    //@OneToMany(cascade = CascadeType.ALL)  
    @OneToMany(fetch = FetchType.EAGER) 
    @JoinTable(name = "COMPUTER_Processor",   
        joinColumns        = {@JoinColumn(name = "Processor_id", referencedColumnName = "id")},  
        inverseJoinColumns = {@JoinColumn(name = "Computer_id", referencedColumnName = "id")}  
    )  
    private Set<Computer> computers;  
    
    /*
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_permissions",
        joinColumns        = { @JoinColumn(name = "role_id",       referencedColumnName = "id") },
        inverseJoinColumns = { @JoinColumn(name = "permission_id", referencedColumnName = "id") }
    )    
    private Set<Permission> permissions;
    */
    @Column(name = "price")
    private int processorprice;
 
    public String getType() {
        return type;
    }
 
    public void setType(String type) {
        this.type = type;
    }
 
    public Set<Computer> getComputers() {
        return computers;
    }
 
    public void setComputers(Set<Computer> computers) {
        this.computers = computers;
    }
    
    public int getPrice() {
    	return processorprice;
    }
    
    public void setPrice(int price) {
    	this.processorprice = price;
    }
    
 
    @Override
    public String toString() {
        return String.format("%s(id=%d, type='%s',price='%d')", 
                this.getClass().getSimpleName(), 
                this.getId(), this.getType(),this.getPrice());
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
 
        if (o instanceof Processor) {
            final Processor other = (Processor) o;
            return Objects.equal(getId(), other.getId())
                    && Objects.equal(getType(), other.getType())
                    && Objects.equal(getPrice(), other.getPrice());
        }
        return false;
    }
 
    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getType(),getPrice());
    }
    
}
