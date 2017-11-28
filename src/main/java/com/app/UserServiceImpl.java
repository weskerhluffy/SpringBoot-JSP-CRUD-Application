/**
 * 
 */
package com.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements IUserService {
	public static final String REST_SERVICE_URI = "https://jsonplaceholder.typicode.com";

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
		User user1 = restTemplate.getForObject(REST_SERVICE_URI + "/posts/"+user.getTelefono(), User.class);
		System.out.println("caca " + user1.getBody());
		return true;
	}

}
