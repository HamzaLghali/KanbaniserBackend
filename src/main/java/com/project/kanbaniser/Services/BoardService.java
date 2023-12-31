package com.project.kanbaniser.Services;

import com.project.kanbaniser.Entities.Board;
import com.project.kanbaniser.Entities.User;
import com.project.kanbaniser.Exceptions.BadRequestException;
import com.project.kanbaniser.Exceptions.BoardNotFoundException;
import com.project.kanbaniser.Exceptions.UserNotFoundException;
import com.project.kanbaniser.Repositories.BoardRepository;
import com.project.kanbaniser.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class BoardService {


	private final BoardRepository boardRepository;
	private final UserRepository userRepository;

	//ajouter un utilisateur
	public void addUser(Board board) {
		boardRepository.save(board);
	}

	public void deleteBoard(int id) {
		if(!boardRepository.existsById(id)) {
			throw new BoardNotFoundException("Board with id "+id+" does not exist");
		}
		boardRepository.deleteById(id);
	}

	public void updateBoard(int id, Board board) {
		if(!boardRepository.existsById(id)) {
			throw new BoardNotFoundException("Board with id "+id+" does not exist");
		}
		board.setTitle(board.getTitle());
		board.setDescription(board.getDescription());
		board.setMembers(board.getMembers());
		board.setTasks(board.getTasks());
		boardRepository.save(board);
	}

	@Transactional
	public void addUserToBoard(int userId, int boardId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found"));
		Board board = boardRepository.findById(boardId)
				.orElseThrow(() -> new BoardNotFoundException("Board not found"));

		board.getMembers().add(user);
		boardRepository.save(board);
	}

}
