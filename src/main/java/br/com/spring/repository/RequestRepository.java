package br.com.spring.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.spring.domain.Request;
import br.com.spring.enuns.RequestState;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
 
	
	public List<Request> findAllByOwnerId(Long id);
	
	@Modifying
	@Transactional
	@Query("update request r set r.state =:state  where r.id =:id")
	public Request updateStatus(@Param("id") Long id, @Param("state") RequestState state);
	
}
