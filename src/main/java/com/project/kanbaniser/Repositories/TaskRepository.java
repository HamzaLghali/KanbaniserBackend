package com.project.kanbaniser.Repositories;

import com.project.kanbaniser.Entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository  extends JpaRepository<Task, Integer> {
}
