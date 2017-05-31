package com.dtr.oas.model;

import java.util.List;

public class OrderFetchEntity extends BaseEntity {
	
	
	private String builddate;
	
    private int price;
	
    private String reason;
    
    private String status;
    
    private String customer;
    
    private String computer;
    
    private String user;
    
    private int number;
    
    private List<Computernumber> computerlist;

	public List<Computernumber> getComputerlist() {
		return computerlist;
	}

	public void setComputerlist(List<Computernumber> computerlist) {
		this.computerlist = computerlist;
	}

	public String getBuilddate() {
		return builddate;
	}

	public void setBuilddate(String builddate) {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}


	public String getComputer() {
		return computer;
	}

	public void setComputer(String computer) {
		this.computer = computer;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
