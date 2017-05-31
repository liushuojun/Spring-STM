package com.dtr.oas.model;

import javax.persistence.Entity;

@Entity
public class ProblemFetchEntity extends BaseEntity{
	
	private String type;
	
	private String description;
	
	private String solution;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}
	
	

}
