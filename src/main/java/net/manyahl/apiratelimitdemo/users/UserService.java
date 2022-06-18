package net.manyahl.apiratelimitdemo.users;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	public List<User> getAllUsers(){
		return userDAO.getAllUsers();
	}
	public Optional<User> getUserById(long id){
		return userDAO.getUserById(id);
	}
}
