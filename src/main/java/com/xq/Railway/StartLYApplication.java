package com.xq.Railway;



import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@MapperScan("com.xq.Railway")
@SpringBootApplication
public class StartLYApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(StartLYApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(StartLYApplication.class, args);
	}
	
}