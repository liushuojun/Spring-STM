package com.dtr.oas.model;


import java.io.Serializable;
import java.util.Date;

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
@Table(name="targets")
public class Target extends BaseEntity implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = -1735845604146131331L;

	static Logger logger = LoggerFactory.getLogger(Target.class);
    
    @NotNull(message = "{error.target.title.null}")
    @NotEmpty(message = "{error.target.title.empty}")
    @Size(max = 50, message = "{error.target.title.max}")
    @Column(name = "title", length = 50)
    private String title;
    
    @Column(name = "start")
    private Date start;

	@Column(name = "end")
    private Date end;
    
    @Size(max = 500, message = "{error.target.url.max}")
    @Column(name = "feedback", length = 500)
    private String feedback;
    
    @Size(max = 300, message = "{error.target.details.max}")
    @Column(name = "details", length = 300)
    private String details;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "target_user",
            joinColumns        = {@JoinColumn(name = "target_id", referencedColumnName = "id")},  
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}         
    )
    private User user;
   
    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}



	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	@Override
	public String toString() {
		return "Target [title=" + title + ", start=" + start + ", end=" + end
				+ ", feedback=" + feedback + ", details=" + details + ", user=" + user
				+ ", getId()=" + getId() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((details == null) ? 0 : details.hashCode());
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((feedback == null) ? 0 : feedback.hashCode());
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
		Target other = (Target) obj;
		if (details == null) {
			if (other.details != null)
				return false;
		} else if (!details.equals(other.details))
			return false;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (feedback == null) {
			if (other.feedback != null)
				return false;
		} else if (!feedback.equals(other.feedback))
			return false;
		return true;
	}
	
}
