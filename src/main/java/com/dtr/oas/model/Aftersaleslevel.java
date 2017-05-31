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
@Table(name = "levels")
public class Aftersaleslevel extends BaseEntity implements Serializable {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = -2679789737224544250L;

	static Logger logger = LoggerFactory.getLogger(Aftersaleslevel.class);
 
    @NotNull(message = "{error.aftersaleslevel.level.null}")
    @NotEmpty(message = "{error.aftersaleslevel.level.empty}")
    @Size(max = 50, message = "{error.aftersaleslevel.level.max}")
    @Column(name = "levelname", length = 50)
    private String levelname;
    
 
    public String getLevel() {
        return levelname;
    }
 
    public void setLevel(String levelname) {
        this.levelname = levelname;
    }
 
    
 
    @Override
    public String toString() {
        return String.format("%s(id=%d, levelname='%s')", 
                this.getClass().getSimpleName(), 
                this.getId(), this.getLevel());
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
 
        if (o instanceof Aftersaleslevel) {
            final Aftersaleslevel other = (Aftersaleslevel) o;
            return Objects.equal(getId(), other.getId())
                    && Objects.equal(getLevel(), other.getLevel());
        }
        return false;
    }
 
    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getLevel());
    }
    
}
