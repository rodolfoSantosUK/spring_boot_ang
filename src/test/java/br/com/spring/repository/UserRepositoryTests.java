package br.com.spring.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
 
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.spring.domain.User;
import br.com.spring.enuns.Role;

@RunWith(SpringRunner.class) 
@SpringBootTest
public class UserRepositoryTests {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void A_saveTest() {
		
		User user = new User(null,"kevin", "kevin@gmail", "123", Role.ADMINISTRATOR, null, null);
		User createdUser = userRepository.save(user);	
		System.out.println(createdUser.getId());
		assertThat(createdUser.getId()).isEqualTo(1L);
	}

	@Test
	public void B_updateTest() {
		
		User user = new User(1L,"kevin santos", "kevin@gmail", "123", Role.ADMINISTRATOR, null, null);
		User updatedUser = userRepository.save(user);		
		assertThat(updatedUser.getName()).isEqualTo("kevin santos");
	}
	
	@Test
	public void C_getByIdTest() {
		Optional<User> result =  userRepository.findById(1L);
		User user = result.get();				
		assertThat(user.getPassword()).isEqualTo("123");
	}
	
	@Test
	public void D_listTest() {
		List<User> users = userRepository.findAll();
		assertThat(users.size()).isEqualTo(1);		
	}
	
	 
	public void loginTest() {
		Optional<User> result = userRepository.login("kevin@gmail", "123");
		User loggerUser = result.get();
		
		assertThat(loggerUser.getId()).isEqualTo(1L);
	}
	
	
	@Test
	void contextLoads() {
		
	}
	
}
