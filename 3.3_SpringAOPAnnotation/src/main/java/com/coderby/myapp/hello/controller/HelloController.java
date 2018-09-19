package com.coderby.myapp.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.coderby.myapp.hello.service.IHelloService;

@Controller
public class HelloController {
	
	@Autowired
	IHelloService helloService;
		
	public void hello(String name) {
		System.out.println("HelloController : " + helloService.sayHello(name));
	}

	public void goodbye(String name) {
		System.out.println("HelloController : " + helloService.sayGoodbye(name));
	}
}