package br.com.acondicional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AcondicionalGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcondicionalGatewayApplication.class, args);
	}

}
