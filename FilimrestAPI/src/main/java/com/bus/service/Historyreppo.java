package com.bus.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bus.beanse.Orderhistory;

public interface Historyreppo extends JpaRepository<Orderhistory, Long> {
		
		@Query(value = "select * from o_history where customer_c_id=? ORDER BY h_id DESC", nativeQuery = true)
		public List<Orderhistory> getAllHistory(long id);

	}


