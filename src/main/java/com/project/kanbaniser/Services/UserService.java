package com.project.kanbaniser.Services;

import com.project.kanbaniser.Entities.User;
import com.project.kanbaniser.Exceptions.UserNotFoundException;
import com.project.kanbaniser.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.project.kanbaniser.Exceptions.BadRequestException;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public void addUser(User user) {

		Boolean existsEmail = userRepository
				.selectExistsEmail(user.getEmail());
		if (existsEmail){
			throw new BadRequestException("Email already exists"+user.getEmail());
		}

		userRepository.save(user);
	}

	public void deleteUser(int id) {
		if(!userRepository.existsById(id)) {
			throw new UserNotFoundException("User with id "+id+" does not exist");
		}
		userRepository.deleteById(id);
	}
}
