package com.coderby.myapp.hello.controller;

import com.coderby.myapp.hello.service.IHelloService;

public class HelloController {
//	IHelloService helloService = new HelloService();
	IHelloService helloService;
	
//	public HelloController(IHelloService helloService) {
//		this.helloService = helloService;
//	}
	
	public void setHelloService(IHelloService helloService) {
		this.helloService = helloService;
	}
	
	public void hello(String name) {
		System.out.println("HelloController : " + helloService.sayHello(name));
	}
}