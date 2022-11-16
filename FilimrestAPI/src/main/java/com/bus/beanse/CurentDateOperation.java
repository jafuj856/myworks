package com.bus.beanse;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="curent_date_operation")
public class CurentDateOperation implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long date_id;
	
	
	@Temporal(TemporalType.DATE)
	private Date show_date;
	
	
	private String show_time;
	
	
	@OneToMany(mappedBy = "operation",fetch = FetchType.EAGER)
	private List<Seat> seat;


	public long getDate_id() {
		return date_id;
	}


	public void setDate_id(long date_id) {
		this.date_id = date_id;
	}


	public Date getShow_date() {
		return show_date;
	}


	public void setShow_date(Date show_date) {
		this.show_date = show_date;
	}


	public String getShow_time() {
		return show_time;
	}


	public void setShow_time(String show_time) {
		this.show_time = show_time;
	}


	public List<Seat> getSeat() {
		return seat;
	}


	public void setSeat(List<Seat> seat) {
		this.seat = seat;
	}



	@Override
	public String toString() {
		return "CurentDateOperation [date_id=" + date_id + ", show_date=" + show_date + ", show_time=" + show_time
				+ ", seat=" + seat + "]";
	}


	public CurentDateOperation(long date_id, Date show_date, String show_time, List<Seat> seat) {
		super();
		this.date_id = date_id;
		this.show_date = show_date;
		this.show_time = show_time;
		this.seat = seat;
	}


	public CurentDateOperation(Date show_date, String show_time, List<Seat> seat) {
		super();
		this.show_date = show_date;
		this.show_time = show_time;
		this.seat = seat;
	}


	public CurentDateOperation() {
		System.out.println(this.getClass().getSimpleName()+" Created");
	}
	
	
	
}
