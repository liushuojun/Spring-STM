package com.dtr.oas.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;


import com.google.common.base.Objects;

@Entity  
@Table(name="customers")
public class Customer extends BaseEntity implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = -7630726211992915857L;

	static Logger logger = LoggerFactory.getLogger(Customer.class);
    
    @NotNull(message = "{error.customer.name.null}")
    @NotEmpty(message = "{error.customer.name.empty}")
    @Size(max = 50, message = "{error.customer.name.max}")
    @Column(name = "Customername", length = 50)
    private String customername;
    
    @Column(name = "employee_num", length = 50)
    private int employee_num;
    
    
    @OneToOne(fetch = FetchType.EAGER)  
    @JoinTable(name = "CUSTOMER_LEVEL",  
        joinColumns        = {@JoinColumn(name = "customer_id", referencedColumnName = "id")},  
        inverseJoinColumns = {@JoinColumn(name = "level_id", referencedColumnName = "id")}  
    )  
    private Aftersaleslevel level;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "CUSTOMER_INDUSTRY",
        joinColumns        = {@JoinColumn(name = "customer_id", referencedColumnName = "id")},  
        inverseJoinColumns = {@JoinColumn(name = "industry_id", referencedColumnName = "id")}     
    )
    private Industry industry;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "CUSTOMER_computer",
    joinColumns        = {@JoinColumn(name = "customer_id", referencedColumnName = "id")},  
    inverseJoinColumns = {@JoinColumn(name = "computer_id", referencedColumnName = "id")}     
    )
    private Computer computer;
    
    @Column(name = "leadname", length = 50)
    private String leadname;
    
    @Column(name = "address", length = 100)
    private String address;
    
    @Column(name = "birthday")
    private Date birthday;
    
    @Column(name = "habit", length = 100)
    private String habit;
    
    @Column(name = "telephone", length = 25)
    private String telephone;
    
    @Column(name = "ordernumber")
    private int ordernumber;
    
    @Column(name = "targetprice")
    private int targetprice;
    
    
    public int getTargetprice() {
		return targetprice;
	}

	public void setTargetprice(int targetprice) {
		this.targetprice = targetprice;
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
    
    public String getCustomername(){
    	return customername;
    }
    
    public int getEmployee_num() {
		return employee_num;
	}

	public void setEmployee_num(int employee_num) {
		this.employee_num = employee_num;
	}

	public void setCustomername(String customername) {
    	this.customername = customername;
    }


    public Aftersaleslevel getLevel() {
        return level;
    }

    public void setLevel(Aftersaleslevel level) {
        this.level = level;
    }
    
    public Industry getIndustry() {
    	return industry;
    }
    
    public void setIndustry(Industry industry) {
    	this.industry = industry;
    }
    

    public int getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(int ordernumber) {
		this.ordernumber = ordernumber;
	}
	

	public Computer getComputer() {
		return computer;
	}

	public void setComputer(Computer computer) {
		this.computer = computer;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;

        if (o instanceof Customer) {
            final Customer other = (Customer) o;
            return Objects.equal(getId(), other.getId())
                    && Objects.equal(getEmployee_num(), other.getEmployee_num())
                    && Objects.equal(getLevel(), other.getLevel())
                    && Objects.equal(getIndustry(), other.getIndustry());
        }
        return false;
    }



	@Override
	public String toString() {
		return "Customer [customername=" + customername + ", employee_num="
				+ employee_num + ", level=" + level + ", industry=" + industry
				+ ", computer=" + computer + ", leadname=" + leadname
				+ ", address=" + address + ", birthday=" + birthday
				+ ", habit=" + habit + ", telephone=" + telephone
				+ ", ordernumber=" + ordernumber + ", getId()=" + getId() + "]";
	}

	@Override
    public int hashCode() {
        return Objects.hashCode(getId(), getEmployee_num());
    }


    
}
