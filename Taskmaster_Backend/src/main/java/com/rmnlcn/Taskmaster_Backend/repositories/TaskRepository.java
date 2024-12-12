package com.rmnlcn.Taskmaster_Backend.repositories;

import com.rmnlcn.Taskmaster_Backend.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
