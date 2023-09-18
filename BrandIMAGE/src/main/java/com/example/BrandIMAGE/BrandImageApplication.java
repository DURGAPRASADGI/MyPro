package com.example.BrandIMAGE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class BrandImageApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrandImageApplication.class, args);
		
		
	}


}
