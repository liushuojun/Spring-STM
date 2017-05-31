package com.dtr.oas.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Objects;
 
@Entity
@Table(name = "industries")
public class Industry extends BaseEntity implements Serializable {
 	/**
	 * 
	 */
	private static final long serialVersionUID = -1318110298834801404L;

	static Logger logger = LoggerFactory.getLogger(Industry.class);
 
    @NotNull(message = "{error.Industry.level.null}")
    @NotEmpty(message = "{error.Industry.level.empty}")
    @Size(max = 50, message = "{error.Industry.level.max}")
    @Column(name = "industryname", length = 50)
    private String industryname;
    
 
    public String getIndustry() {
        return industryname;
    }
 
    public void setIndustry(String industryname) {
        this.industryname = industryname;
    }
 
    
 
    @Override
    public String toString() {
        return String.format("%s(id=%d, industryname='%s')", 
                this.getClass().getSimpleName(), 
                this.getId(), this.getIndustry());
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
 
        if (o instanceof Industry) {
            final Industry other = (Industry) o;
            return Objects.equal(getId(), other.getId())
                    && Objects.equal(getIndustry(), other.getIndustry());
        }
        return false;
    }
 
    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getIndustry());
    }
}
