package br.com.spring.service;
 

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import br.com.spring.domain.RequestStage;
import br.com.spring.enuns.RequestState;
import br.com.spring.repository.RequestStageRepository; 

@Service
public class RequestStageService {

	@Autowired
	private RequestStageRepository requestStageRepository;

	public RequestStage save(RequestStage requestStage) {

		requestStage.setState(RequestState.OPEN);		
		requestStage.setRealizationDate(new Date());
		
		RequestStage createdRequestStage = requestStageRepository.save(requestStage);
		return createdRequestStage;
		
	}

	public RequestStage update(RequestStage request) {
		RequestStage updatedRequestStage = requestStageRepository.save(request);
		return updatedRequestStage;
	}

	public RequestStage getById(Long id) {
		Optional<RequestStage> result = requestStageRepository.findById(id);
		return result.get();
	}
	
	
	public List<RequestStage> listAll() {
		 List<RequestStage>  requestsStages = requestStageRepository.findAll();
		 return requestsStages;
	}
	
	
	public List<RequestStage> listAllByRequestId(Long ownerId) {
		 List<RequestStage>  requests = requestStageRepository.findAllByRequestId(ownerId);
		 return requests;
	}
	

}
