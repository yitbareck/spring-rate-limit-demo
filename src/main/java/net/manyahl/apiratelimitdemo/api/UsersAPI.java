package net.manyahl.apiratelimitdemo.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.manyahl.apiratelimitdemo.exceptions.ErrorResponse;
import net.manyahl.apiratelimitdemo.exceptions.TooManyRequestException;
import net.manyahl.apiratelimitdemo.exceptions.UserNotFoundException;
import net.manyahl.apiratelimitdemo.limiters.Limiter;
import net.manyahl.apiratelimitdemo.users.User;
import net.manyahl.apiratelimitdemo.users.UserService;


@RestController
@RequestMapping("/api/v1")
public class UsersAPI {
	
	@Autowired
	private UserService userService;
	
	//Rate limiter: supports multiple implementations 
	@Qualifier("tokenBucket")
	@Autowired
	private Limiter limiter;
	
	@Value("${rateLimit.numberOfRequests}")
	private long rate;
	
	@Value("${rateLimit.minutes}")
	private long minute;
	
	//Limited route
	@GetMapping("/users")
	public List<User> getAllUsers(){
		if(limiter.isRequestValid())
				return userService.getAllUsers();
		throw new TooManyRequestException(String.format("You are not allowed to make more than %d request(s) in %d seconds",rate,minute * 60));
	}
	
	//unlimited route
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable long id) {
		Optional<User> optionalUser = userService.getUserById(id);
		if(optionalUser.isPresent()) return optionalUser.get();
		throw new UserNotFoundException("User with the specified id doesn't exit");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(UserNotFoundException exception){
		ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(TooManyRequestException exception){
		ErrorResponse response = new ErrorResponse(HttpStatus.TOO_MANY_REQUESTS.value(), exception.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<>(response,HttpStatus.TOO_MANY_REQUESTS);
	}

}
