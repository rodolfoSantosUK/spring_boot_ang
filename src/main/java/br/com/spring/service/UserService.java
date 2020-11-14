package br.com.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spring.domain.User;
import br.com.spring.repository.UserRepository;
import br.com.spring.service.util.HashUtil;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public User save(User user) {

		String hash = HashUtil.getSecureHash(user.getPassword());
		user.setPassword(hash);
		
		User createdUser = repository.save(user);
		return createdUser;

	}

	public User update(User user) {
		
		String hash = HashUtil.getSecureHash(user.getPassword());
		user.setPassword(hash);
		
		User createdUser = repository.save(user);
		return createdUser;
	}

	public User getById(Long id) {
		Optional<User> result = repository.findById(id);
		return result.get();
	}
	
	
	public List<User> listAll() {
		 List<User>  users = repository.findAll();
		 return users;
	}
	
	
	public User login (String email, String password) {
		
		password = HashUtil.getSecureHash(password);
		
		Optional<User> result = repository.login(email, password);
		return result.get();		
	}
	

}
