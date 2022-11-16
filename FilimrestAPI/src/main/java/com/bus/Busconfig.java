package com.bus;

import javax.persistence.Column;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bus.flitter.Busflitter;
@Configuration
public class Busconfig implements WebMvcConfigurer {

public Busflitter Getflitter() {
	
	return new Busflitter();
}
@Override
	public void addInterceptors(InterceptorRegistry registry) {
	
	  registry.addInterceptor(Getflitter()).addPathPatterns("/*");
	}
}
