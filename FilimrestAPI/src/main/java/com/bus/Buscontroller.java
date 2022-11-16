package com.bus;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bus.beanse.Customer;
import com.bus.beanse.MovieDetailse;
import com.bus.beanse.Orderhistory;
import com.bus.beanse.Seat;

import com.bus.service.CusmtemerDAO;
@Controller
public class Buscontroller {

	@Autowired
	public CusmtemerDAO dao;
	
	@GetMapping("/")
	public String home(Model m, HttpSession session) {
	
		String movie=(String)session.getAttribute("moviename");
		System.out.println(movie+"----------Index");
		List<MovieDetailse> movie2 = dao.getallmovies();
		
		
		m.addAttribute("movielist",movie2);
	    m.addAttribute("menu","Home");
		return "index";
	}
	
	//page 2
	
	@GetMapping("/booking")
	public String bookingcheck(@Param("moviename")String moviename,Model m,HttpSession session) {
		
		
		List<MovieDetailse> movie2 = dao.getallmovies();
		List<String> checkMovie = new ArrayList<>();
		
		for (MovieDetailse string : movie2) {
			checkMovie.add(string.getMoviename());
		}
		System.out.println(moviename);
		if (checkMovie.contains(moviename)) {
			session.setAttribute("moviename", moviename);
			
			System.out.println(moviename+"  Booking movieName");
			
			LocalDate now = LocalDate.now();
			LocalDate monthLimit = LocalDate.now();
			String time = "09:00 am";

			Customer customer = (Customer) session.getAttribute("user");
			
			System.out.println(customer+"      this is customer");
			
			List<String> seatNo1 = new ArrayList<String>();
			List<Seat> seat = customer.getSeat();

			List<Seat> all = dao.getallseats(now, time);

			for (Seat s : all) {
				for (String s1 : s.getSeatNo()) {
					seatNo1.add(s1);
				}

			}

			m.addAttribute("date", now);
			m.addAttribute("time", time);
			m.addAttribute("max", monthLimit.plusMonths(1));
			m.addAttribute("min", monthLimit);
			m.addAttribute("seats", seatNo1);
			m.addAttribute("seat", seat);
			session.setAttribute("user", customer);
			return "/Home";
		} else {
			System.out.println("hiiiiiiii");
			return "redirect:/";
		}

	}	
	
	//registerformloding
	@GetMapping("/register")
	public String rgister(Model m) {
		
		m.addAttribute("menu","register");
		return"/register";
	}
	
//loginform
	@GetMapping("/login")
	public String login(Model m) {
		m.addAttribute("menu","login");
		
		return"login";
	}
	
	//registrationprosses
	@PostMapping("/save")
	public String save(@ModelAttribute("customer")Customer customer) {
		System.out.println(customer.getEmail());
		dao.save(customer);
		return"/register";
	}
	
//	login prosses
	
	@PostMapping("/processing")
	public String loginprosses(@RequestParam("email")String email,@RequestParam("password")String password,HttpSession session,Model m) {
		Customer object=(Customer)session.getAttribute("user");
		System.out.println(object+"     this Is use");
		if(object!=null)
		{
			return "/booking-seat";
		}
		else {
			System.out.println(" else section");
			Customer customer=dao.login(email, password);
			
			if(customer==null)
			{
				m.addAttribute("failed","Invalid Login");
				return"login";
			}
			else {
				session.setAttribute("user", customer);
			}
		}
		
		return "/Home";
	}
	
	
	@GetMapping("/Home")
	public String mainDashboard(HttpSession session, Model m) {
		session.removeAttribute("bookingdate");
		session.removeAttribute("bookingtime");
		session.removeAttribute("movieName");
		m.addAttribute("menu", "Home");

		String message = (String) session.getAttribute("msg");
		m.addAttribute("message", message);
		session.removeAttribute("msg");
		List<MovieDetailse> movie2 = dao.getallmovies();
		m.addAttribute("listMovie", movie2);

		return "/main-dashbord";
	}
	
	
	//bookingProsses
	@PostMapping("/booking-seat")
	public String booking(@ModelAttribute("Seat")Seat seat,@RequestParam("moviename")String Moviename,HttpSession session,Model m) {
		
		LocalDate currentDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date todayDate = Date.from(currentDate.atStartOfDay(defaultZoneId).toInstant());
		LocalDate date = (LocalDate) session.getAttribute("bookingdate");
		String time = (String) session.getAttribute("bookingtime");
		System.out.println(seat.getSeatNo().equals(null) + " wooo" + Moviename.equals(null));
		 Customer object=(Customer)session.getAttribute("user");
		 if(object==null) {
			 
			 return "/login";
		 }
		 else {
			 return "/Home";
		}
		
	}
	
	
	
//	Logout process
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");

		session.removeAttribute("bookingdate");
		session.removeAttribute("bookingtime");
		session.removeAttribute("movieName");

		return "redirect:/";
	}
	
	
	
	
	@PostMapping("/check")
	public String checkDate(@RequestParam("localdate") String date, @RequestParam("localtime") String time, Model m,
			HttpSession session) {
		Customer object = (Customer) session.getAttribute("user");
		System.out.println(object  +"inCheck");
		String movie = (String) session.getAttribute("moviename");
		System.out.println(movie);
		LocalDate monthLimit = LocalDate.now();
		if (movie.equals(null)) {
			System.out.println("check in movi ==="+movie);
			return "/Home";

		} else if (object == null) {
			LocalDate now = LocalDate.parse(date);
			List<String> seatNo1 = new ArrayList<String>();
			List<Seat> all = dao.getallseats(now, time);

			for (Seat s : all) {
				for (String s1 : s.getSeatNo()) {
					seatNo1.add(s1);
				}

			}

			session.setAttribute("bookingdate", now);
			session.setAttribute("bookingtime", time);
			m.addAttribute("date", now);
			m.addAttribute("max", monthLimit.plusMonths(1));
			m.addAttribute("min", monthLimit);
			m.addAttribute("time", time);
			m.addAttribute("seats", seatNo1);

			return "/Home";
		} else {
			System.out.println("els in check=========");
			
			
			LocalDate now = LocalDate.now();
		
			System.out.println(now);
			
			List<String> seatNo1 = new ArrayList<String>();
			List<Seat> all = dao.getallseats(now, time);

			for (Seat s : all) {
				for (String s1 : s.getSeatNo()) {
					seatNo1.add(s1);
				}

			}

			session.setAttribute("bookingdate", now);
			session.setAttribute("bookingtime", time);
			m.addAttribute("date", now);
			m.addAttribute("max", monthLimit.plusMonths(1));
			m.addAttribute("min", monthLimit);
			m.addAttribute("time", time);
			m.addAttribute("seats", seatNo1);

			return "/dashbord";
		}
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	//Seat booking process
	@PostMapping("/book-seat")
	public String bookSeat(@ModelAttribute("Seat") Seat seat, @RequestParam("moviename") String moviename,
			HttpSession session, Model m) {
		LocalDate currentDate = LocalDate.now();
		System.out.println(currentDate);
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date todayDate = Date.from(currentDate.atStartOfDay(defaultZoneId).toInstant());
		LocalDate date = (LocalDate) session.getAttribute("bookingdate");
		String time = (String) session.getAttribute("bookingtime");
		System.out.println(time);
		System.out.println(seat.getSeatNo().equals(null) + " wooo" + moviename.equals(null));
		Customer object = (Customer) session.getAttribute("user");
        System.out.println("this is book seat  "+object);
		if (object == null) {
			return "/loginForm";
		} else if ((seat.getSeatNo().isEmpty()) && (moviename.equals(null))) {
			System.out.println("Seat is null");
			return "/Home";
		} else if (date == null) {
			date = currentDate;
			time = "09:00 am";
			if (((date.isAfter(currentDate)) || (date.equals(currentDate)))
					&& (date.isBefore(currentDate.plusMonths(1)) || date.equals(currentDate.plusMonths(1)))) {

				Date date2 = Date.from(date.atStartOfDay(defaultZoneId).toInstant());
				List<Double> price = new ArrayList<Double>();
				double sum = 0;
				double p = 525.22d;
				for (String s : seat.getSeatNo()) {
					sum = sum + p;
					price.add(p);
				}
				seat.setTotal(sum);
				seat.setPirce(price);

				Orderhistory history = new Orderhistory(seat.getSeatNo(), price, sum, moviename, todayDate, date2, time,
						object);
				dao.saveSeat(seat, object, date2, time);
				dao.saveHistory(history, object);
				List<String> seatNo1 = new ArrayList<String>();
				List<Customer> all = dao.getAll();
				for (Customer c : all) {
					for (Seat s : c.getSeat()) {
						for (String s1 : s.getSeatNo()) {
							seatNo1.add(s1);
						}

					}
				}

				m.addAttribute("seats", seatNo1);
				session.setAttribute("user", object);
				session.setAttribute("msg", "your seat book successsfully");
				return "/Home";

			} else {
				System.out.println("ye date current date se pahle ki date hai");
				return "/booking-seat?movieName=" + moviename;

			}
		} else {
			if (((date.isAfter(currentDate)) || (date.equals(currentDate)))
					&& (date.isBefore(currentDate.plusMonths(1)) || date.equals(currentDate.plusMonths(1)))) {
				Date date2 = Date.from(date.atStartOfDay(defaultZoneId).toInstant());
				List<Double> price = new ArrayList<Double>();
				double sum = 0;
				double p = 525.22d;
				for (String s : seat.getSeatNo()) {
					sum = sum + p;
					price.add(p);
				}
				seat.setTotal(sum);
				seat.setPirce(price);

				Orderhistory history = new Orderhistory(seat.getSeatNo(), price, sum, moviename, todayDate, date2, time,
						object);
				dao.saveSeat(seat, object, date2, time);
				dao.saveHistory(history, object);
				List<String> seatNo1 = new ArrayList<String>();
				List<Customer> all = dao.getAll();
				for (Customer c : all) {
					for (Seat s : c.getSeat()) {
						for (String s1 : s.getSeatNo()) {
							seatNo1.add(s1);
						}

					}
				}

				m.addAttribute("seats", seatNo1);
				session.setAttribute("user", object);
				session.setAttribute("msg", "your seat book successsfully");
				return "/Home";

			} else {
				System.out.println("ye date current date se pahle ki date hai");
				return "/booking-seat?movieName=" + moviename;

			}
		}

	}
//	Order history
	@GetMapping("/order-history")
	public String history(HttpSession session, Model m) {
		Date todayDate = new Date();
		Customer object = (Customer) session.getAttribute("user");
		session.setAttribute("user", object);
		List<Orderhistory> list = dao.getAllHistory(object.getC_id());
		m.addAttribute("hList", list);
		m.addAttribute("todaydate", todayDate);

		LocalDate date = (LocalDate) session.getAttribute("bookingdate");
		System.out.println(date);
		m.addAttribute("menu", "order");
		return "history";
	}
	
//	Exception handling
	@ExceptionHandler(Exception.class)
	public String handleError(Exception ex, Model m, HttpSession session) {
		Customer object = (Customer) session.getAttribute("user");
		if (object == null) {
			return "redirect:/loginForm";
		} else {
			return "redirect:/home";
		}
	}
}