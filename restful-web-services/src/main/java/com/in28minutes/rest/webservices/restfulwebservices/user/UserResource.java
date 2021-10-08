package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService userService;

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		List<User> users = userService.findAll();
		if (users.isEmpty())
			throw new UserNotFoundException("Users Are Empty");
		return users;
	}

	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		User user = userService.findOne(id);
		if (user == null)
			throw new UserNotFoundException("id-" + id);
		return user;
	}

	@DeleteMapping("/users/{id}")
	public User deleteUser(@PathVariable int id) {
		User user = userService.deleteById(id);
		if (user == null)
			throw new UserNotFoundException("id " + id + " Not found for deletion");
		return user;
	}

	@PostMapping("/users")
	public ResponseEntity createUser(@RequestBody User user) {
		User savedUser = userService.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
}
