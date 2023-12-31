package com.project.kanbaniser.ServicesTest;

import com.project.kanbaniser.Entities.User;
import com.project.kanbaniser.Repositories.UserRepository;
import com.project.kanbaniser.Services.UserService;
import com.project.kanbaniser.Exceptions.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;

	public UserServiceTest() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void getAllUsersTest() {
		List<User> users = Arrays.asList(new User(1, "John", "Doe", "1234567890", "john@example.com", "password", new ArrayList<>(), new ArrayList<>()));
		when(userRepository.findAll()).thenReturn(users);

		List<User> result = userService.getAllUsers();

		assertThat(result).isEqualTo(users);
	}

	@Test
	void getUserByIdTest() {
		int userId = 1;
		User user = new User(userId, "John", "Doe", "1234567890", "john@example.com", "password", new ArrayList<>(), new ArrayList<>());
		when(userRepository.findById(userId)).thenReturn(Optional.of(user));

		User result = userService.getUserById(userId);

		assertThat(result).isEqualTo(user);
	}

	@Test
	void getUserByEmailTest() {
		String email = "john@example.com";
		User user = new User(1, "John", "Doe", "1234567890", email, "password", new ArrayList<>(), new ArrayList<>());
		when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

		User result = userService.getUserByEmail(email);

		assertThat(result).isEqualTo(user);
	}

	@Test
	void addUserTest() {
		User user = new User(1, "John", "Doe", "1234567890", "john@example.com", "password", new ArrayList<>(), new ArrayList<>());
		when(userRepository.save(user)).thenReturn(user);

		userService.addUser(user);

		verify(userRepository, times(1)).save(user);
	}

	@Test
	void updateUserTest() {
		int userId = 1;
		User existingUser = new User(userId, "John", "Doe", "1234567890", "john@example.com", "password", new ArrayList<>(), new ArrayList<>());
		when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));

		User updatedUser = new User(userId, "Updated", "User", "9876543210", "updated@example.com", "newpassword", new ArrayList<>(), new ArrayList<>());
		userService.updateUser(userId, updatedUser);

		assertThat(existingUser).isEqualTo(updatedUser);
	}

	@Test
	void deleteUserTest() {
		int userId = 1;

		userService.deleteUser(userId);

		verify(userRepository, times(1)).deleteById(userId);
	}

	// Add more test methods as needed

}
