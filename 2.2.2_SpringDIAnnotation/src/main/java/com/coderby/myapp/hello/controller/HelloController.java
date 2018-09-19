package com.coderby.myapp.hello.controller;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.coderby.myapp.hello.service.IHelloService;

@Controller
public class HelloController {

	@Autowired
//	@Qualifier("niceService")
	IHelloService service;
	
	public void hello(String name) {
		System.out.println("@HelloController : " + service.sayHello(name));
	}
}