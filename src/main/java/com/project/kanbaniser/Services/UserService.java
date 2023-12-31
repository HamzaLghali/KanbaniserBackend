package com.project.kanbaniser.Services;

import com.project.kanbaniser.Entities.Board;
import com.project.kanbaniser.Entities.Task;
import com.project.kanbaniser.Entities.User;
import com.project.kanbaniser.Exceptions.UserNotFoundException;
import com.project.kanbaniser.Repositories.BoardRepository;
import com.project.kanbaniser.Repositories.TaskRepository;
import com.project.kanbaniser.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.project.kanbaniser.Exceptions.BadRequestException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class UserService {

	private final UserRepository userRepository;
	private final BoardRepository boardRepository;


	//liste des utilisateurs
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	//ajouter un utilisateur
	public void addUser(User user) {

		Boolean existsEmail = userRepository
				.selectExistsEmail(user.getEmail());
		if (existsEmail){
			throw new BadRequestException("Email already exists"+user.getEmail());
		}

		userRepository.save(user);
	}


	//modifier un utilisateur
	public void updateUser(int id, User user) {
		if(!userRepository.existsById(id)) {
			throw new UserNotFoundException("User with id "+id+" does not exist");
		}

		user.setFirstname(user.getFirstname());
		user.setLastname(user.getLastname());
		user.setPhoneNumber(user.getPhoneNumber());
		user.setEmail(user.getEmail());
		user.setPassword(user.getPassword());

		userRepository.save(user);
	}

	//supprimer un utilisateur
	public void deleteUser(int id) {
		if(!userRepository.existsById(id)) {
			throw new UserNotFoundException("User with id "+id+" does not exist");
		}
		userRepository.deleteById(id);
	}

	//rechercher un utilisateur par id
	public User getUserById(int id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User with id "+id+" does not exist"));
	}

	//rechercher un utilisateur par email
	public User getUserByEmail(String email) {
		return (User) userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException("User with email "+email+" does not exist"));
	}
}
