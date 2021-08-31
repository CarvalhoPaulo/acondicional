package br.com.acondicional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AcondicionalUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcondicionalUserApplication.class, args);
	}

}
