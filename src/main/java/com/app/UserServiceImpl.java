/**
 * 
 */
package com.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.core.ParameterizedTypeReference;

@Service
public class UserServiceImpl implements IUserService {
	public static final String REST_SERVICE_URI = "https://postman-echo.com/basic-auth";

	private IUser userRepo;

	// Spring Setter Injection
	@Autowired
	public void setUserRepo(IUser userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public Iterable<User> listAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User getUserById(long id) {
		return userRepo.findOne(id);
	}

	@Override
	public User saveUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public void deleteUser(long id) {
		userRepo.delete(id);

	}

	public Boolean enviarCodigo(User user) {
		// XXX: http://websystique.com/spring-boot/spring-boot-rest-api-example/
		RestTemplate restTemplate = new RestTemplate();
		// XXX:
		// http://www.baeldung.com/how-to-use-resttemplate-with-basic-authentication-in-spring
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("postman", "password"));
		/*
		 * User user1 = restTemplate.getForObject(REST_SERVICE_URI, User.class);
		 * System.out.println("caca " + user1);
		 */
		ResponseEntity<User> ruser = restTemplate.exchange(REST_SERVICE_URI, HttpMethod.GET, null, User.class);
		HttpStatus statusCode = ruser.getStatusCode();
		User user1 = ruser.getBody();
		System.out.println("caca " + user1 + " estatus " + statusCode);
		user.setAuthenticated(user1.getAuthenticated());
		return user1.getAuthenticated();
	}

}
