package net.manyahl.apiratelimitdemo.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class UserDAOImpl implements UserDAO {
	private List<User> users;
	
	@PostConstruct
	private void generateUsers() {
		users = new ArrayList<User>();
		users.add(new User(1,"Yitbarek Z","ytbareck@gmail.com"));
		users.add(new User(2,"Hana B.","hana.belay@gmail.com"));
	}
	@Override
	public List<User> getAllUsers() {
		return users;
	}

	@Override
	public Optional<User> getUserById(long id) {
		return users.stream().filter(user -> user.getId() == id).findFirst();
	}

}
