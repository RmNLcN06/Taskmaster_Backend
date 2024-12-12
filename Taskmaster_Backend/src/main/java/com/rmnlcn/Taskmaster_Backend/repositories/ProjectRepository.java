package com.rmnlcn.Taskmaster_Backend.repositories;

import com.rmnlcn.Taskmaster_Backend.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
