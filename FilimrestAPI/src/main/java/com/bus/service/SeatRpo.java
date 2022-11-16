package com.bus.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bus.beanse.Seat;

@Repository
public interface SeatRpo extends JpaRepository<Seat, Long> {

	@Query(value = "select * from seat where customer_b_id=?", nativeQuery = true)
	public List<Seat> getAllSeat(long id);
	
	@Query(value = "select * from seat inner join seat_seat_no"
			+ " on seat.s_Id = seat_seat_no.seat_s_Id"
		    + " inner join curent_date_operation"
		    + " on seat.operation_date_id = curent_date_operation.date_id"
			+ " where show_date = ? and show_time = ?", nativeQuery = true)
	public List<Seat> getAllByDate(LocalDate date, String time);
}
