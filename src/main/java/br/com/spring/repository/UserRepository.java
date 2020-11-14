package br.com.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.spring.domain.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long>{

	@Query("SELECT u FROM user u WHERE u.email =:email and u.password =:password")
	public Optional<User> login (@Param("email") String email, @Param("password") String password);
	
}
