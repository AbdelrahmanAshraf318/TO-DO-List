package com.example.TO_DO.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
public class ToDoListApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToDoListApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<HiddenHttpMethodFilter> hiddenHttpMethodFilter() {
		FilterRegistrationBean<HiddenHttpMethodFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new HiddenHttpMethodFilter());
		registrationBean.addUrlPatterns("/todos/*");  // Apply it to all /todos/* endpoints
		return registrationBean;
	}
}
