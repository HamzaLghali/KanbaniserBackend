package com.project.kanbaniser.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;

	private String firstname;

	private String lastname;

	private String phoneNumber;

	private String email;

	private String password;

	@OneToMany(mappedBy = "createdBy")
	private List<Task> tasks;


}
