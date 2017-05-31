package com.dtr.oas.model;

import java.util.Date;

public class TargetEntity extends BaseEntity{
	
	private String title;
	
	private Date start;
	
	private Date end;
	
	private String feedback;
	
	private String details;
	
	private int userId;
	
	private String user;

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



	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "TargetEntity [title=" + title + ", start=" + start + ", end="
				+ end + ", feedback=" + feedback + ", details=" + details + ", userId="
				+ userId + ", getId()=" + getId() + "]";
	}
		

}
