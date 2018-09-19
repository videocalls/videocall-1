package com.coderby.myapp.hello.service.proxy;

import org.springframework.stereotype.Component;

import com.coderby.myapp.hello.service.HelloService;
import com.coderby.myapp.util.HelloLog;

@Component("helloServiceProxy")
public class HelloServiceProxy extends HelloService {

	@Override
	public String sayHello(String name) {
		HelloLog.log();			//공통코드를 실행합니다.
		return super.sayHello(name); 	//핵심코드를 실행합니다.
	}

	@Override
	public String sayGoodbye(String name) {
		return "Goodbye~" + name;
	}
}