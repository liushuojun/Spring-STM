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
@Table(name = "status")
public class Status extends BaseEntity implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5474751734685631996L;

	static Logger logger = LoggerFactory.getLogger(Status.class);
 
    @NotNull(message = "{error.status.type.null}")
    @NotEmpty(message = "{error.status.type.empty}")
    @Size(max = 50, message = "{error.status.type.max}")
    @Column(name = "type", length = 50)
    private String type;
 
    public String getType() {
        return type;
    }
 
    public void setType(String type) {
        this.type = type;
    }
 
    @Override
    public String toString() {
        return String.format("%s(id=%d, type='%s'')", 
                this.getClass().getSimpleName(), 
                this.getId(), this.getType());
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
 
        if (o instanceof Status) {
            final Status other = (Status) o;
            return Objects.equal(getId(), other.getId())
                    && Objects.equal(getType(), other.getType());
        }
        return false;
    }
 
    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getType());
    }
    
}
