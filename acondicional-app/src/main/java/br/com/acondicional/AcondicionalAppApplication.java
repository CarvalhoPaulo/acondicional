package br.com.acondicional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AcondicionalAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcondicionalAppApplication.class, args);
	}

}
