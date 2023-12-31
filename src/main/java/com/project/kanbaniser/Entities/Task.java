package com.project.kanbaniser.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;

	private String Title;

	private String Description;

	private Date createdAt;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "createdBy")
	private User createdBy;

}
