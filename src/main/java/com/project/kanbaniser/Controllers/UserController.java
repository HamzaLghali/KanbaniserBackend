package com.project.kanbaniser.Controllers;

import com.project.kanbaniser.Entities.Board;
import com.project.kanbaniser.Entities.Task;
import com.project.kanbaniser.Entities.User;
import com.project.kanbaniser.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/api/v1/user")
@AllArgsConstructor
public class UserController {

	private final UserService userService;


	//lister les utilisateurs
	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	//ajouter un utilisateur
	@PostMapping
	public void addUser(@RequestBody User user) {
		userService.addUser(user);
	}

	//modifier un utilisateur
	@PutMapping(path = "{userId}")
	public void updateUser(@PathVariable("userId") int id, @RequestBody User user) {
		userService.updateUser(id, user);
	}

	//supprimer un utilisateur
	@DeleteMapping(path = "{userId}")
	public void deleteUser(@PathVariable("userId") int id) {
		userService.deleteUser(id);
	}

	//rechercher un utilisateur par id
	@GetMapping(path = "{userId}")
	public User getUserById(@PathVariable("userId") int id) {
		return userService.getUserById(id);
	}

	//rechercher un utilisateur par email
	@GetMapping(path = "{userEmail}")
	public User getUserByEmail(@PathVariable("userEmail") String email) {
		return userService.getUserByEmail(email);
	}

	//liste des boards d'un utilisateur
	@GetMapping(path = "{userId}/boards")
	public List<Board> getBoardsByUserId(@PathVariable("userId") int id) {
		return userService.getBoardsByUserId(id);
	}

	//liste des taches d'un utilisateur
	@GetMapping(path = "{userId}/tasks")
	public List<Task> getTasksByUserId(@PathVariable("userId") int id) {
		return userService.getTasksByUserId(id);
	}
}
