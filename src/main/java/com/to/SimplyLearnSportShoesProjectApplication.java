package com.to;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.to.filters.AdminAuthFilter;
import com.to.filters.AuthFilter;
import com.to.services.UserService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SimplyLearnSportShoesProjectApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	UserService userService;

	public static void main(String[] args) {

		SpringApplication.run(SimplyLearnSportShoesProjectApplication.class, args);
	}

	// registerning a user filter bean
	@Bean
	public FilterRegistrationBean<AuthFilter> filterRegistrationBean() {
		FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
		AuthFilter authFilter = new AuthFilter();
		registrationBean.setFilter(authFilter);
		// set the url for scanning the requests
		registrationBean.addUrlPatterns("/api/userPurchase/*");
		return registrationBean;
	}

	// registering an admin filter bean
	@Bean
	public FilterRegistrationBean<AdminAuthFilter> adminfilterRegistrationBean() {
		FilterRegistrationBean<AdminAuthFilter> registrationBean = new FilterRegistrationBean<>();
		AdminAuthFilter adminAuthFilter = new AdminAuthFilter();
		registrationBean.setFilter(adminAuthFilter);
		// set the url for scanning the request
		registrationBean.addUrlPatterns("/api/admin/category/*", "/api/admin/product/*", "/api/admin/report/*");
		return registrationBean;
	}

	@Override
	public void run(String... args) throws Exception {

	}

}