package com.bus.beanse;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "moviesdetails")
public class MovieDetailse implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
	private long movieid;
	@Column(nullable = false)
	private String moviename;
	@Column(nullable = false)
	private String image;
	@Column(nullable = false)
	private String moviedetailse;
	
	
	
	public long getMovieid() {
		return movieid;
	}
	public void setMovieid(long movieid) {
		this.movieid = movieid;
	}
	public String getMoviename() {
		return moviename;
	}
	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getMoviedetailse() {
		return moviedetailse;
	}
	public void setMoviedetailse(String moviedetailse) {
		this.moviedetailse = moviedetailse;
	}
	@Override
	public String toString() {
		return "MovieDetailse [movieid=" + movieid + ", moviename=" + moviename + ", image=" + image
				+ ", moviedetailse=" + moviedetailse + "]";
	}
	
public MovieDetailse() {
	System.out.println(this.getClass().getSimpleName()+" created");
}
}
