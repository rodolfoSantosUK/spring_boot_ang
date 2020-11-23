package br.com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.spring.domain.RequestStage;

public interface RequestStageRepository extends JpaRepository<RequestStage, Long> {

	
	public List<RequestStage> findAllByRequestId(Long id);
	
}
