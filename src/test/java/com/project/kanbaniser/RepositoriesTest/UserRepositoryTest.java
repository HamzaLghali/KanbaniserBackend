package com.project.kanbaniser.RepositoriesTest;

import com.project.kanbaniser.Entities.User;
import com.project.kanbaniser.Repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testCreateReadDelete() {
		// Create a user
		User user = new User();
		user.setFirstname("Test");
		user.setLastname("User");
		user.setPhoneNumber("1234567890");
		user.setEmail("test.user@example.com");
		user.setPassword("password");

		// Save the user to the repository
		user = userRepository.save(user);

		// Assert that the user was saved correctly
		assertThat(user.getUserId()).isNotNull();
		assertThat(user.getFirstname()).isEqualTo("Test");
		assertThat(user.getLastname()).isEqualTo("User");
		assertThat(user.getPhoneNumber()).isEqualTo("1234567890");
		assertThat(user.getEmail()).isEqualTo("test.user@example.com");
		assertThat(user.getPassword()).isEqualTo("password");

		// Retrieve the user from the repository
		User foundUser = userRepository.findById(user.getUserId()).orElse(null);
		assertThat(foundUser).isNotNull();
		assertThat(foundUser.getFirstname()).isEqualTo("Test");
		assertThat(foundUser.getLastname()).isEqualTo("User");
		assertThat(foundUser.getPhoneNumber()).isEqualTo("1234567890");
		assertThat(foundUser.getEmail()).isEqualTo("test.user@example.com");
		assertThat(foundUser.getPassword()).isEqualTo("password");

		// Optionally, test update operations here
		// ...

		// Delete the user
		userRepository.delete(user);

		// Verify that the user no longer exists in the repository
		assertThat(userRepository.findById(user.getUserId())).isEmpty();
	}
}
