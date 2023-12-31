package com.project.kanbaniser.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title;

	private String description;

	@ManyToMany
	@JoinTable(
			name="board_members",
			joinColumns = @JoinColumn(name = "Boardid"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private List<User> members;

	@OneToMany(mappedBy = "board")
	private List<Task> tasks;
}
