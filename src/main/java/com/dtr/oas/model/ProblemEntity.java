package com.dtr.oas.model;

import javax.persistence.Entity;

@Entity
public class ProblemEntity extends BaseEntity{
	
	private int typeId;
	
	private String description;
	
	private String solution;

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
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
