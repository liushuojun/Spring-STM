package com.dtr.oas.model;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity  
@Table(name="orders")
public class Order extends BaseEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6649041501095526081L;

	static Logger logger = LoggerFactory.getLogger(Order.class);
    
    @Column(name = "builddate", length = 50)
    private Date builddate;
       
    
    @Column(name = "price")
    private int price;
    
    @Column(name = "reason", length = 500)
    private String reason;
    
    @Column(name = "number")
    private int number;
    
    @OneToOne(fetch = FetchType.EAGER)  
    @JoinTable(name = "order_status",  
        joinColumns        = {@JoinColumn(name = "Order_id", referencedColumnName = "id")},  
        inverseJoinColumns = {@JoinColumn(name = "status_id", referencedColumnName = "id")}  
    )  
    private Status status;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "order_customer",
        joinColumns        = {@JoinColumn(name = "Order_id", referencedColumnName = "id")},  
        inverseJoinColumns = {@JoinColumn(name = "customer_id", referencedColumnName = "id")}     
    )
    private Customer customer;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "order_computer",
        joinColumns        = {@JoinColumn(name = "Order_id", referencedColumnName = "id")},  
        inverseJoinColumns = {@JoinColumn(name = "computer_id", referencedColumnName = "id")}            		
 	)
    private Computer computer;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "order_user",
            joinColumns        = {@JoinColumn(name = "Order_id", referencedColumnName = "id")},  
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}         
    )
    private User user;
    
    @OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.ALL})
    @JoinTable(name = "order_computernumber",
    joinColumns        = {@JoinColumn(name = "order_id", referencedColumnName = "id")},  
    inverseJoinColumns = {@JoinColumn(name = "computernumber_id", referencedColumnName = "id")}         
    )
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Computer getComputer() {
		return computer;
	}

	public void setComputer(Computer computer) {
		this.computer = computer;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
		result = prime * result
				+ ((builddate == null) ? 0 : builddate.hashCode());
		result = prime * result
				+ ((computer == null) ? 0 : computer.hashCode());
		result = prime * result
				+ ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + price;
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Order other = (Order) obj;
		if (builddate == null) {
			if (other.builddate != null)
				return false;
		} else if (!builddate.equals(other.builddate))
			return false;
		if (computer == null) {
			if (other.computer != null)
				return false;
		} else if (!computer.equals(other.computer))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (price != other.price)
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [builddate=" + builddate + ", price=" + price
				+ ", reason=" + reason + ", status=" + status + ", customer="
				+ customer + ", user=" + user + ", computerlist="
				+ computerlist + ", getId()=" + getId() + "]";
	}


		
    
}
