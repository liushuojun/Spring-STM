package com.dtr.oas.model;


import java.io.Serializable;
import java.util.Date;

//@Entity  
public class CustomerEntity extends BaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5223700682863151115L;
	
	private  String customername;
	
	private int employee_num;
	
	private int levelId;
	
	private int industryId;
	
	private String leadname;
	
	private String address;
	
	private Date birthday;
	
	private String habit;
	
	private String telephone;
	
	private int ordernumber;
	
	private int computerId;
	
	private int targetprice;
	

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public int getEmployee_num() {
		return employee_num;
	}

	public void setEmployee_num(int employee_num) {
		this.employee_num = employee_num;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public int getIndustryId() {
		return industryId;
	}

	public void setIndustryId(int industryId) {
		this.industryId = industryId;
	}

	public String getLeadname() {
		return leadname;
	}

	public void setLeadname(String leadname) {
		this.leadname = leadname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}



	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getHabit() {
		return habit;
	}

	public void setHabit(String habit) {
		this.habit = habit;
	}
	

	public int getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(int ordernumber) {
		this.ordernumber = ordernumber;
	}
	

	public int getComputerId() {
		return computerId;
	}

	public void setComputerId(int computerId) {
		this.computerId = computerId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getTargetprice() {
		return targetprice;
	}

	public void setTargetprice(int targetprice) {
		this.targetprice = targetprice;
	}
	
   
}
