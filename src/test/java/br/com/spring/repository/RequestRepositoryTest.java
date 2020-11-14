package br.com.spring.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.spring.domain.Request;
import br.com.spring.domain.User;
import br.com.spring.enuns.RequestState; 

@RunWith(SpringRunner.class) 
@SpringBootTest
public class RequestRepositoryTest {

	@Autowired
	private RequestRepository requestRepository;

	@Test
	public void A_saveTest() {
		
		User owner = new User();
		owner.setId(1L);
		
		Request request = new Request(null, "Novo laptop","quero laptop", new Date(),
				RequestState.IN_PROGRESS, owner, null );
		
		Request createdRequest = requestRepository.save(request);
		 
		assertThat(createdRequest.getId()).isEqualTo(1L);
	}

	@Test
	public void B_updateTest() {
		
		User owner = new User();
		owner.setId(1L);
		
		Request request = new Request(null, "Novo laptop atualizado","quero laptop", new Date(),
				RequestState.IN_PROGRESS, owner, null );
		
		Request createdRequest = requestRepository.save(request);
		 
		assertThat(createdRequest.getDescription()).isEqualTo("Novo laptop atualizado");
	}
	
	@Test
	public void C_getByIdTest() {
		Optional<Request> result =  requestRepository.findById(1L);
		Request request = result.get();				
		assertThat(request.getId()).isEqualTo(1L);
	}
	
	@Test
	public void D_listTest() {
		List<Request> users = requestRepository.findAll();
		assertThat(users.size()).isEqualTo(1);		
	}
	
	 
	@Test
	public void D_listByOwnerIdTest() {
		List<Request> requests = requestRepository.findAllByOwnerId(1L);
		assertThat(requests.size()).isEqualTo(1);		
	}
	
	
	public void updateStatusTest() {
		Request affectedRows =  requestRepository.updateStatus(1L, RequestState.IN_PROGRESS );
		assertThat(affectedRows.getState()).isEqualTo(RequestState.IN_PROGRESS);
	
	}
	
	
	
}
