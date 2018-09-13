package com.others;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.rest.restfulwebservices.user.User;
import com.microservices.rest.restfulwebservices.user.UserDaoService;


public class SomeApplication {
	
	@Autowired
	UserDaoService userDaoService;

	public SomeApplication() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void displayAllUsers() {
		List<User> users = userDaoService.findAll();
		System.out.println(users.size());
	}
	
	
	public static void main(String[] args) {
		SomeApplication some = new SomeApplication();
		some.displayAllUsers();
		

	}

	
	
	

}
