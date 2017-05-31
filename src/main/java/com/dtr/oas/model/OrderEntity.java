package com.dtr.oas.model;

import java.util.Date;
import java.util.List;

public class OrderEntity extends BaseEntity {
	
	private Date builddate;
	
    private int price;
	
    private String reason;
    
    private int statusId;
    
    private int customerId;
    
    //private int computerId;
    
    private int userId;
    
    //private int number;
    
    private List<Computernumber> computerlist;

	public List<Computernumber> getComputerlist() {
		return computerlist;
	}

	public void setComputerlist(List<Computernumber> computerlist) {
		this.computerlist = computerlist;
	}

	public Date getBuilddate() {
		return builddate;
	}

	public void setBuilddate(Date builddate) {
		this.builddate = builddate;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

/*	public int getComputerId() {
		return computerId;
	}

	public void setComputerId(int computerId) {
		this.computerId = computerId;
	}*/

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "OrderEntity [builddate=" + builddate + ", price=" + price
				+ ", reason=" + reason + ", statusId=" + statusId
				+ ", customerId=" + customerId + ", userId=" + userId
				+ ", computerlist=" + computerlist + ", getId()=" + getId()
				+ "]";
	}

/*	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}*/
	

}
