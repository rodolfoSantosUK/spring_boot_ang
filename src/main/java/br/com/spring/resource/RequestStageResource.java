package br.com.spring.resource;
 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import br.com.spring.domain.RequestStage;
import br.com.spring.service.RequestStageService;

@RestController
@RequestMapping(value = "request-stage")
public class RequestStageResource {

	@Autowired
	private RequestStageService requestStageService;

	
	@PostMapping
	public ResponseEntity<RequestStage> save(@RequestBody RequestStage requestStage) {

		RequestStage createdRequestStage = requestStageService.save(requestStage);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdRequestStage);

	}

	@PutMapping("{/id}")
	public ResponseEntity<RequestStage> update(@PathVariable(name = "id") Long id, @RequestBody RequestStage requestStage ) {

		requestStage.setId(id);
		RequestStage updatedRequestStage = requestStageService.update(requestStage);
		return ResponseEntity.ok(updatedRequestStage);
	}

	@GetMapping("{/id}")
	public ResponseEntity<RequestStage> getById(@PathVariable(name = "id") Long id) {
		RequestStage requestStage = requestStageService.getById(id);
		return ResponseEntity.ok(requestStage);
	}
	
	@GetMapping 
	public ResponseEntity<List <RequestStage>> listAll() {
		List <RequestStage> requestsStage = requestStageService.listAll(); 
		return ResponseEntity.ok(requestsStage);
	}
	 
	

}
