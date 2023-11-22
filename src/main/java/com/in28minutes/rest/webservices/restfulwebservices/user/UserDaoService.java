package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;


@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<>();
	
	private static Integer userCount =0;
	
//	static {
//		users.add(new User(++userCount,"we",LocalDate.now().minusYears(25)));
//		users.add(new User(++userCount,"kim",LocalDate.now().minusYears(55)));
//		users.add(new User(++userCount,"chui",LocalDate.now().minusYears(14)));
//	}
	
	
	public List<User> findAll(){
		return users;
	}
	
	public User findOne(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id); 
		return users.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void deleteById(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id); 
		users.removeIf(predicate);
	}
	
	public User save(User user) {
		user.setId(++userCount);
		users.add(user);
		return user;
	}
}
