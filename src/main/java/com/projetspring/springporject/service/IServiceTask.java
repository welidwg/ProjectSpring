package com.projetspring.springporject.service;

import com.projetspring.springporject.entity.Status;
import com.projetspring.springporject.entity.Task;

import java.util.List;

public interface IServiceTask {
    public void saveTask(Task task);
    public List<Task> getTasks();
    public Task getTaskById(Long id);

    public void deleteTask(Long id);
}
