package com.project.kanbaniser.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
	private List<User> members = new ArrayList<>();

	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Task> tasks = new ArrayList<>();
}
