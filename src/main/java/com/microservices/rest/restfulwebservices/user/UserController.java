package com.microservices.rest.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import static  org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {
	
	@Autowired
	private UserDaoService userDaoService;
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return userDaoService.findAll();
	}
	
	@GetMapping("/users/{id}")
	public Resource<User> findOneUser(@PathVariable int id) {
		
		User user = userDaoService.findOne(id);
		if(null==user) {
			throw new UserNotFoundException("id-" + id);
		}
		
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		// links to all users
		resource.add(linkTo.withRel("all-users"));
		
		return resource;
		
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userDaoService.save(user);
		
		//users/{id} savedUser.getId()
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId())
		.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = userDaoService.deleteUserByIdUsingIterator(id);

		if(null==user) {
			throw new UserNotFoundException("id-" + id);
		}
		
	}
	
	@GetMapping("/users/oldversion/{id}")
	public User findOneUserv1(@PathVariable int id) {
		
		User user = userDaoService.findOne(id);
		if(null==user) {
			throw new UserNotFoundException("id-" + id);
		}
		return user;
		
	}

}
