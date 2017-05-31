package com.dtr.oas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "computernumber")
public class Computernumber extends BaseEntity implements Serializable{


	
    /**
	 * 
	 */
	private static final long serialVersionUID = -4689471370201642535L;

	static Logger logger = LoggerFactory.getLogger(Computernumber.class);
    
    @Column(name = "computerId")
    private int computerId;
    
    @Column(name = "number")
    private int number;

	public int getComputerId() {
		return computerId;
	}

	public void setComputerId(int computerId) {
		this.computerId = computerId;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + computerId;
		result = prime * result + number;
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
		Computernumber other = (Computernumber) obj;
		if (computerId != other.computerId)
			return false;
		if (number != other.number)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Computernumber [computerId=" + computerId + ", number="
				+ number + ", getId()=" + getId() + "]";
	}



}
