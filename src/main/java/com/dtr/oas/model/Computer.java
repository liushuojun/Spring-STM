package com.dtr.oas.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.google.common.base.Objects;

@Entity  
@Table(name="computer")
public class Computer extends BaseEntity implements Serializable {
	/*
	 * CREATE TABLE COMPUTER ( 
	 * ID INT(6) UNSIGNED NOT NULL AUTO_INCREMENT,
	 * SERIES VARCHAR(50) NOT NULL, 
	 * PRICE INT(6) NOT NULL, 
	 * PRIMARY KEY (ID) )
	 * COLLATE='utf8_general_ci' ENGINE=InnoDB AUTO_INCREMENT=1;
	 */	
	private static final long serialVersionUID = -8416748518929258320L;

    static Logger logger = LoggerFactory.getLogger(Computer.class);
    
    @NotNull(message = "{error.computer.name.null}")
    @NotEmpty(message = "{error.computer.name.empty}")
    @Size(max = 50, message = "{error.computer.name.max}")
    @Column(name = "Computername", length = 50)
    private String computername;
    
    @NotNull(message = "{error.computer.series.null}")
    @NotEmpty(message = "{error.computer.series.empty}")
    @Size(max = 50, message = "{error.computer.series.max}")
    @Column(name = "series", length = 50)
    private String series;
    
    
    @Column(name = "price")
    private int price;
    
    @OneToOne(fetch = FetchType.EAGER)  
    @JoinTable(name = "COMPUTER_Processor",  
        joinColumns        = {@JoinColumn(name = "Computer_id", referencedColumnName = "id")},  
        inverseJoinColumns = {@JoinColumn(name = "Processor_id", referencedColumnName = "id")}  
    )  
    private Processor processor;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "COMPUTER_System",
        joinColumns        = {@JoinColumn(name = "Computer_id", referencedColumnName = "id")},  
        inverseJoinColumns = {@JoinColumn(name = "System_id", referencedColumnName = "id")}     
    )
    private System system;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "computer_memory",
        joinColumns        = {@JoinColumn(name = "Computer_id", referencedColumnName = "id")},  
        inverseJoinColumns = {@JoinColumn(name = "Memory_id", referencedColumnName = "id")}            		
 	)
    private Memory memory;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "computer_display",
            joinColumns        = {@JoinColumn(name = "Computer_id", referencedColumnName = "id")},  
            inverseJoinColumns = {@JoinColumn(name = "Display_id", referencedColumnName = "id")}         
    )
    private Display display;
    
    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }
    
    public String getComputername(){
    	return computername;
    }
    
    public void setComputername(String computername) {
    	this.computername = computername;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int nprice) {
        this.price = nprice;
    }


    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }
    
    public System getSystem() {
    	return system;
    }
    
    public void setSystem(System os) {
    	this.system = os;
    }
    
    public Memory getMemory() {
    	return memory;
    }
    
    public void setMemory(Memory memory) {
    	this.memory = memory;
    }
    
    public Display getDisplay() {
    	return display;
    }
    
    public void setDisplay(Display display) {
    	this.display = display;
    }

    @Override
    public String toString() {
        return String.format("%s(id=%d, series=%s, price=%d)", 
                this.getClass().getSimpleName(), 
                this.getId(), 
                this.getSeries(), 
                this.getPrice()); 
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;

        if (o instanceof Computer) {
            final Computer other = (Computer) o;
            return Objects.equal(getId(), other.getId())
                    && Objects.equal(getSeries(), other.getSeries())
                    && Objects.equal(getPrice(), other.getPrice());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getSeries(), getPrice());
    }
    
}