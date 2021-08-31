package br.com.acondicional.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "user-api/v1")
public class UserController {
	
	@GetMapping
	public String findAll() {
		return "Hello user-api v1";
	}
}
