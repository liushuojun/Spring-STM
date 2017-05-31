package com.dtr.oas.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
 
@Entity
@Table(name = "Reports")
public class Report extends BaseEntity implements Serializable {
 
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 8436266097313636170L;

    @NotNull(message = "{error.target.title.null}")
    @NotEmpty(message = "{error.target.title.empty}")
    @Size(max = 500, message = "{error.target.title.max}")
    @Column(name = "description", length = 500)
    private String description;
    
    @Column(name = "orderId")
    private int orderId;
    
    @Column(name = "solution", length = 50)
    private String solution;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + orderId;
		result = prime * result
				+ ((solution == null) ? 0 : solution.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Report other = (Report) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (orderId != other.orderId)
			return false;
		if (solution == null) {
			if (other.solution != null)
				return false;
		} else if (!solution.equals(other.solution))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Report [description=" + description + ", orderId=" + orderId
				+ ", solution=" + solution + ", getId()=" + getId() + "]";
	}

    
	
	
       
}
