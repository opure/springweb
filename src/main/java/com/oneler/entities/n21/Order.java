package com.oneler.entities.n21;

import javax.annotation.Generated;
import javax.persistence.*;

@Entity
@Table(name = "MyOrder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;

	private String orderName;

    @OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}




	
}
