package net.manyahl.apiratelimitdemo.users;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
	public List<User> getAllUsers();
	public Optional<User> getUserById(long id);
}
