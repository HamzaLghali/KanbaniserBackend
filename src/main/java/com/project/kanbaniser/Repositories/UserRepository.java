package com.project.kanbaniser.Repositories;

import com.project.kanbaniser.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.email = ?1")
	Boolean selectExistsEmail(String email);

	@Query("SELECT u FROM User u WHERE u.email = ?1")
	Optional<Object> findByEmail(String email);
}
