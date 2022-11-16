package com.bus.beanse;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="seat")
public class Seat implements Serializable{
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="s_Id")
	private long sid;
	@ElementCollection
    private List<String> seatNo;
	@ElementCollection
    private List<Double> pirce;
	
	private double total;
	
	@ManyToOne(cascade =CascadeType.ALL,fetch = FetchType.EAGER )
	private Customer customer;
	
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private CurentDateOperation operation;


	public long getSid() {
		return sid;
	}


	public void setSid(long sid) {
		this.sid = sid;
	}


	public List<String> getSeatNo() {
		return seatNo;
	}


	public void setSeatNo(List<String> seatNo) {
		this.seatNo = seatNo;
	}


	public List<Double> getPirce() {
		return pirce;
	}


	public void setPirce(List<Double> pirce) {
		this.pirce = pirce;
	}


	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public CurentDateOperation getOpration() {
		return operation;
	}


	public void setOpration(CurentDateOperation opration) {
		this.operation = opration;
	}







	public Seat(long sid, List<String> seatNo, List<Double> pirce, double total, Customer customer,
			CurentDateOperation operation) {
		super();
		this.sid = sid;
		this.seatNo = seatNo;
		this.pirce = pirce;
		this.total = total;
		this.customer = customer;
		this.operation = operation;
	}


	@Override
	public String toString() {
		return "Seat [sid=" + sid + ", seatNo=" + seatNo + ", pirce=" + pirce + ", total=" + total + ", customer="
				+ customer + ", operation=" + operation + "]";
	}





}
