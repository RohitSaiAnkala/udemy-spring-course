package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<>();
	private static int usersCount = 3;
	static {
		users.add(new User(1, "Adam", new Date()));
		users.add(new User(2, "Eve", new Date()));
		users.add(new User(3, "Jack", new Date()));
	}

	public List<User> findAll() {
		return users;
	}

	public User save(User user) {
		if (user.getId() == null)
			user.setId(++usersCount);
		users.add(user);
		return user;
	}

	public User findOne(int id) {
		List<User> list = users.stream().filter(user -> user.getId().intValue() == id)
				.collect(Collectors.toList());
		if (list.isEmpty())
			return null;
		return list.get(0);

	}

	public User deleteById(int id) {
		for (User user : users) {
			if (user.getId() == id) {
				users.remove(user);
				return user;
			}
		}
		return null;
	}
	
}
