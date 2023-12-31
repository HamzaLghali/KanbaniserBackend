package com.project.kanbaniser.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "app_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;

	private String firstname;

	private String lastname;

	private String phoneNumber;

	private String email;

	private String password;

	@ManyToMany(mappedBy = "members")
	private List<Board> boards;

	@OneToMany(mappedBy = "createdBy")
	private List<Task> tasks = new ArrayList<>();


}
