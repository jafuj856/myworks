package com.bus.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bus.beanse.CurentDateOperation;
import com.bus.beanse.Customer;
import com.bus.beanse.MovieDetailse;
import com.bus.beanse.Orderhistory;
import com.bus.beanse.Seat;



@Component
public class CusmtemerDAO {
 
	@Autowired
	private MovieReppo movirepo;
	

	
	@Autowired
	private CustomerRepo customerepo;
	
	
	@Autowired
	private SeatRpo seatrepo;
	
	@Autowired
	private Historyreppo historyreppo;
	
	
	public List<MovieDetailse> getallmovies() {
		List<MovieDetailse>  list=this.movirepo.findAll();
		return list;
	}
	
	public List<Seat> getseat(long id)
	{
		List<Seat> list=this.seatrepo.getAllSeat(id);
		return list;
		
		}
		
		
		
	
	

	
	public int save(Customer customer) {
		customerepo.save(customer);
	
		return 1;
	}
	
	public Customer login(String email,String password) {
	
	Customer customer=customerepo.findByEmailAndPassword(email,password);
	
	return customer;
	}
	
	public List<Seat> getallseats(LocalDate date,String time){
		
		 List<Seat> seat=seatrepo.getAllByDate(date, time);
		 
		 return seat;
	}
	
	
	public int saveSeat(Seat seat, Customer customer, Date date, String time){
		List<Seat> list = new ArrayList<Seat>();
		list.add(seat);
		customer.setSeat(list);
		CurentDateOperation cdo= new CurentDateOperation();
		cdo.setShow_date(date);
		cdo.setShow_time(time);
		cdo.setSeat(list);
		
		seat.setOpration(cdo);
		seat.setOpration(cdo);
		seat.setCustomer(customer);
		Seat save = seatrepo.save(seat);
		return 1;
	}
	
	public List<Customer> getAll(){
		List<Customer> findAll = customerepo.findAll();
		
		System.out.println(findAll+"===== find all  ");
		
		return findAll;
	}
	
	public Orderhistory saveHistory(Orderhistory history, Customer customer) {
		customer.setOrderhistory(history);
		Orderhistory save = historyreppo.save(history);
		return save;
	}

	public List<Orderhistory> getAllHistory(long c_id) {
		// TODO Auto-generated method stub
		return null;
	}
}
