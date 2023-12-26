package com.projetspring.springporject.repository;

import com.projetspring.springporject.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    public Task findByTitle(String title);
    public List<Task> findByTitleContaining(String mc);
    public Page<Task> findByTitleContaining(String mc, Pageable p);

    
}
