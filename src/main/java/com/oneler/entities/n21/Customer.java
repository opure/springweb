package com.oneler.entities.n21;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;


	private String customerName;

	private String cutomerColer;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(
			String customerName) {
		this.customerName = customerName;
	}

	public String getCutomerColer() {
		return cutomerColer;
	}

	public void setCutomerColer(String cutomerColer) {
		this.cutomerColer = cutomerColer;
	}
}
