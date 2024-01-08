package com.projetspring.springporject.service;

import com.projetspring.springporject.entity.AppUser;
import com.projetspring.springporject.entity.Status;
import com.projetspring.springporject.entity.Task;
import com.projetspring.springporject.entity.TaskAssignment;
import com.projetspring.springporject.repository.TaskAssignmentRepository;
import com.projetspring.springporject.repository.TaskRepository;
import com.pusher.rest.Pusher;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class ServiceAssignment implements IServiceAssignment {

    private TaskAssignmentRepository assignmentRepository;
    private ServiceTask serviceTask;
    @Autowired
    private IServiceAccount serviceAccount;
    private ServiceStatus serviceStatus;
    @Autowired
    Pusher pusher;



    @Override
    public void addTaskToUser(String id, Long taskID, Date dueDate, Date assignDate, Long status_id) {
        Task task = serviceTask.getTaskById(taskID);
        AppUser user = serviceAccount.getAppUserById(id);
        boolean check = existByTaskAndUser(task, user);
        Status status = serviceStatus.getStatusById(status_id);
        if (check)
            throw new RuntimeException("Already aasigned to this user");
        if (user != null && task != null && !check){
            assignmentRepository.save(TaskAssignment.builder().user(user).task(task).id(UUID.randomUUID().toString()).dueDate(dueDate).assignemntDate(assignDate).status(status).build());
            pusher.trigger("notif-"+user.getId(),"notif-event","You have a new task! ' "+task.getTitle()+" '");

        }


    }


    @Override
    public void UpdateStatus(Long status_id, String assignmentID) {
        TaskAssignment assignment = assignmentRepository.findById(assignmentID).orElse(null);
        if (assignment != null) {
            Status status = serviceStatus.getStatusById(status_id);
            assignment.setStatus(status);
            if(status.getLabel().equals("Finished")){
                assignment.setFinishedDate(Date.valueOf(LocalDate.now()));
            }else if (status.getLabel().equals("Waiting")){
                assignment.setFinishedDate(null);
            }
            pusher.trigger("notif-admin","notif-event-admin",assignment.getUser().getUsername()+" has updated his task "+assignment.getTask().getTitle()+" status : "+status.getLabel());

            //System.out.println("status id : " + assignment.getStatus().getLabel());
        }

    }

    @Override
    public boolean existByTaskAndUser(Task task, AppUser user) {
        return assignmentRepository.existsByTaskAndUser(task, user);
    }

    @Override
    public boolean existByUser(AppUser user) {
        return assignmentRepository.existsByUser(user);
    }

    @Override
    public List<TaskAssignment> getAssignmentsByUser(AppUser user) {
        return assignmentRepository.findTaskAssignmentByUser(user) ;
    }

    @Override
    public void deleteAssignment(String id) {
        assignmentRepository.deleteById(id);
    }


}
