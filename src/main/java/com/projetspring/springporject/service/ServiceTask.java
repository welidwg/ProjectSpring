package com.projetspring.springporject.service;

import com.projetspring.springporject.entity.Task;
import com.projetspring.springporject.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ServiceTask implements IServiceTask{
    @Autowired
    TaskRepository taskRepository;

    @Override
    public void saveTask(Task t) {
//        Task task=taskRepository.findByTitle(t.getTitle());
        taskRepository.save(t);
    }

    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public List<Task> getTaskByMC(String mc) {
        return taskRepository.findByTitleContaining(mc);
    }

    @Override
    public Page<Task> getTaskByMC(String mc, Pageable p) {
        return taskRepository.findByTitleContaining(mc,p);
    }


    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
