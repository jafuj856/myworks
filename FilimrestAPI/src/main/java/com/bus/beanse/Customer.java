package com.bus.beanse;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name="customer")
public class Customer implements Serializable{
 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long c_id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false,unique = true)
	private String email;
	@Column(nullable = false)
	private String password;
	

	@OneToMany(mappedBy = "customer",fetch = FetchType.EAGER)
	private List<Seat> seat;
	
	
	public Customer(long c_id, String name, String email, String password, List<Seat> seat) {
		super();
		this.c_id = c_id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.seat = seat;
	}


	@OneToOne(mappedBy = "customer",fetch = FetchType.EAGER)
	private Orderhistory orderhistory;


	public List<Seat> getSeats() {
		return seat;
	}


	public void setSeats(List<Seat> seat) {
		this.seat = seat;
	}


	public long getC_id() {
		return c_id;
	}


	public void setC_id(long c_id) {
		this.c_id = c_id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public List<Seat> getSeat() {
		return seat;
	}


	public void setSeat(List<Seat> seat) {
		this.seat = seat;
	}


	public Orderhistory getOrderhistory() {
		return orderhistory;
	}


	public void setOrderhistory(Orderhistory orderhistory) {
		this.orderhistory = orderhistory;
	}




	
	public Customer() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass().getSimpleName()+" Created");
	}


	public Customer(String name, String email, String password, List<Seat> seat) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.seat = seat;
	}




	@Override
	public String toString() {
		return "Customer [c_id=" + c_id + ", name=" + name + ", email=" + email + ", password=" + password + ", seat="
				+ seat + "]";
	}
}
