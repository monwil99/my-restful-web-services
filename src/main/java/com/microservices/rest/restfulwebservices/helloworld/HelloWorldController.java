package com.microservices.rest.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {

	//GET
	//URI - /hello-world
	//method - "Hello World"
	
	/*@RequestMapping(method=RequestMethod.GET, path="hello-world")
	public String helloWorld() {
		return "Hello RequestMapping";
	}*/
	
	@GetMapping(path="hello-world")
	public String helloWorld() {
		return "Hello GetMapping";
	}
	
	@GetMapping(path="hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World My Bean");
	}
	
	//hello-world/path-variable/in28minutes
	@GetMapping(path="hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldBean(@PathVariable String name) {
		//return new HelloWorldBean(name);
		return new HelloWorldBean(String.format("Hello World, %s", name));
	}
}
