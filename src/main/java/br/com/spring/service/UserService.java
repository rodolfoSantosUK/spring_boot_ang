package br.com.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.spring.domain.User;
import br.com.spring.model.PageModel;
import br.com.spring.model.PageRequestModel;
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

	public Optional<User> getById(Long id) {
		Optional<User> result = repository.findById(id);
		return result;
	}

	public List<User> listAll() {
		List<User> users = repository.findAll();
		return users;
	}

    public PageModel<User> listAllOnLazyModel(PageRequestModel pr) {
		
    	Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
    	Page<User> page =   repository.findAll(pageable);
		
    	PageModel<User> pm = new PageModel((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
    
    	return pm;
	}

	public User login(String email, String password) {

		password = HashUtil.getSecureHash(password);

		Optional<User> result = repository.login(email, password);
		return result.get();
	}

}
