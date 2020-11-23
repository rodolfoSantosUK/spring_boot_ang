package br.com.spring.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.spring.domain.Request;
import br.com.spring.domain.User;
import br.com.spring.dto.UserLoginDTO;
import br.com.spring.exception.NotFoundException;
import br.com.spring.model.PageModel;
import br.com.spring.model.PageRequestModel;
import br.com.spring.service.RequestService;
import br.com.spring.service.UserService;

@RestController
@RequestMapping(value = "users")
public class UserResource {

	@Autowired
	private UserService userService;

	@Autowired
	private RequestService requestService;

	@PostMapping
	public ResponseEntity<User> save(@RequestBody User user) {

		User createdUser = userService.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);

	}

	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable(name = "id") Long id, @RequestBody User user) {

		user.setId(id);
		User updatedUser = userService.update(user);
		return ResponseEntity.ok(updatedUser);
	}

	@GetMapping("/{id}")
	public User getById(@PathVariable(name = "id") Long id) {

		Optional<User> result = userService.getById(id);
		 return result.orElseThrow(()-> new NotFoundException(" Não existe usuario com o id") ) ;
		 
	}

	@GetMapping
	public ResponseEntity< PageModel<User>> listAll(@RequestParam(value = "page", defaultValue = "0" ) int page,
													@RequestParam(value = "size", defaultValue = "10" ) int size) {
		
        PageRequestModel pr = new PageRequestModel(page,size);
        PageModel<User> pm = userService.listAllOnLazyModel(pr);

		return ResponseEntity.ok(pm);
	}
	
	
	

	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody UserLoginDTO userLoginDTO) {

		User loggedUser = userService.login(userLoginDTO.getEmail(), userLoginDTO.getPassword());
		return ResponseEntity.status(HttpStatus.CREATED).body(loggedUser);

	}

	@GetMapping("/{id}/requests")
	public ResponseEntity<List<Request>> listAllRequestsById(@PathVariable(name = "id") Long id) {
		List<Request> requests = requestService.listAllByOwnerId(id);
		return ResponseEntity.ok(requests);

	}

}
