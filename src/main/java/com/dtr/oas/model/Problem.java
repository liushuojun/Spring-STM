package com.dtr.oas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity  
@Table(name="problems")
public class Problem extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7071928476270976059L;
	
	 static Logger logger = LoggerFactory.getLogger(Computer.class);
	 
	    @NotNull(message = "{error.problem.description.null}")
	    @NotEmpty(message = "{error.problem.description.empty}")
	    @Size(max = 500, message = "{error.problem.description.max}")
	    @Column(name = "description", length = 500)
	    private String description;
	    
	    @NotNull(message = "{error.problem.solution.null}")
	    @NotEmpty(message = "{error.problem.solution.empty}")
	    @Size(max = 500, message = "{error.problem.solution.max}")
	    @Column(name = "solution", length = 500)
	    private String solution;
	    
	    @OneToOne(fetch = FetchType.EAGER)  
	    @JoinTable(name = "Problem_type",  
	        joinColumns        = {@JoinColumn(name = "problem_ID", referencedColumnName = "id")},  
	        inverseJoinColumns = {@JoinColumn(name = "type_ID", referencedColumnName = "id")}  
	    )  
	    private Type type;

		public Type getType() {
			return type;
		}

		public void setType(Type type) {
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

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = super.hashCode();
			result = prime * result
					+ ((description == null) ? 0 : description.hashCode());
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
			Problem other = (Problem) obj;
			if (description == null) {
				if (other.description != null)
					return false;
			} else if (!description.equals(other.description))
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
			return "Problem [getType()=" + getType() + ", getDescription()="
					+ getDescription() + ", getSolution()=" + getSolution()
					+ ", getId()=" + getId() + "]";
		}


		
}
