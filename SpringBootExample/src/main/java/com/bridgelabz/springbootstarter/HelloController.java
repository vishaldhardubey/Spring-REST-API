package com.bridgelabz.springbootstarter;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	//This way of mapping is for get request.
	@RequestMapping("/Hello")
	public String sayHi() {
		return "Hi";
	}
}
