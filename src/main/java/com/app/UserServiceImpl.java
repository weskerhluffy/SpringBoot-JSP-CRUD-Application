/**
 * 
 */
package com.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import org.springframework.web.client.RestTemplate;

import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.core.ParameterizedTypeReference;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
	public static final String REST_SERVICE_URI = "https://postman-echo.com/basic-auth";

	private IUser userRepo;
	@Autowired
	private UserDao userDao;

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
		Random caca = new Random();

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
		user.setUserId(caca.nextInt(100000));
		System.out.println("caca " + user1 + " estatus " + statusCode);
		user.setAuthenticated(user1.getAuthenticated());
		return user1.getAuthenticated();
	}

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public User findBySSO(String ssoId) {
		assert userDao.findBySSO(ssoId).equals(userRepo.findBySSO(ssoId));
		return userDao.findBySSO(ssoId);
	}

}
