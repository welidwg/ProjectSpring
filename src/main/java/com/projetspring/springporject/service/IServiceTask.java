package com.projetspring.springporject.service;

import com.projetspring.springporject.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IServiceTask {
    public void saveTask(Task task);
    public List<Task> getTasks();
    public Task getTaskById(Long id);
    public List<Task> getTaskByMC(String mc);
    public Page<Task> getTaskByMC(String mc, Pageable p);
    public void deleteTask(Long id);
}
