package com.projetspring.springporject.repository;

import com.projetspring.springporject.entity.AppUser;
import com.projetspring.springporject.entity.Task;
import com.projetspring.springporject.entity.TaskAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskAssignmentRepository extends JpaRepository<TaskAssignment,String> {
    public List<TaskAssignment> findTaskAssignmentByUser(AppUser user);
    public TaskAssignment findTaskAssignmentByTask(Task task);
    public boolean existsByTaskAndUser(Task task,AppUser user);
    public boolean existsByUser(AppUser user);
}
