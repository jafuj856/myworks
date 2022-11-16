package com.bus.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bus.beanse.MovieDetailse;
@Repository
public interface MovieReppo extends JpaRepository<MovieDetailse, Long> {

}
