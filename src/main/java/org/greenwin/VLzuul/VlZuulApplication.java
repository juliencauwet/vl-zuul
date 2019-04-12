package org.greenwin.VLzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class VlZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(VlZuulApplication.class, args);
	}

	@Bean
	BCryptPasswordEncoder getBCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
}

