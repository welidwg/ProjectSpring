package com.projetspring.springporject.service;

import com.projetspring.springporject.entity.AppUser;
import com.projetspring.springporject.entity.Status;
import com.projetspring.springporject.entity.Task;
import com.projetspring.springporject.entity.TaskAssignment;
import jakarta.mail.MessagingException;

import java.sql.Date;
import java.util.List;

public interface IServiceAssignment {
    public void addTaskToUser(String id, Long taskID, Date dueDate, Date assignmentDate, Long status_id) throws MessagingException;
    public void UpdateStatus(Long status_id,String assignmentID);
    public boolean existByTaskAndUser(Task task, AppUser user);
    public boolean existByUser(AppUser user);
    public List<TaskAssignment> getAssignmentsByUser(AppUser user);
    public void deleteAssignment(String id);

}
