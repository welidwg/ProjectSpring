package com.projetspring.springporject.controller;

import com.projetspring.springporject.entity.AppUser;
import com.projetspring.springporject.entity.Status;
import com.projetspring.springporject.entity.TaskAssignment;
import com.projetspring.springporject.service.ServiceAccount;
import com.projetspring.springporject.service.ServiceAssignment;

import com.pusher.rest.Pusher;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class AssignmentController {
    @Autowired
    ServiceAssignment serviceAssignment;
    @Autowired
    ServiceAccount serviceAccount;
    @Autowired
    Pusher pusher;

    @PostMapping("/admin/task/assign")
    public String taskAssign(@RequestBody TaskAssignment taskAssignment) {
        serviceAssignment.addTaskToUser(taskAssignment.getUser().getId(), taskAssignment.getTask().getId(), taskAssignment.getDueDate(), taskAssignment.getAssignemntDate(), taskAssignment.getStatus().getId());
        return "done";
    }

    @PutMapping("/user/task/{id}/status")
    public String statusUpdate(@RequestBody Status status, @PathVariable String id) {
        serviceAssignment.UpdateStatus(status.getId(), id);
        return status.getId() + "";
    }

    @GetMapping("/user/assignment/user/{id}")
    public List<TaskAssignment> getAssignmentsByUser(@PathVariable String id, @RequestParam(defaultValue = "") String mc) {
        AppUser user = serviceAccount.getAppUserById(id);
        return serviceAssignment.getAssignmentsByUser(user);
    }

    @DeleteMapping("/admin/assignment/{id}/delete")
    public void deleteAssignment(@PathVariable String id) {
        serviceAssignment.deleteAssignment(id);

    }
}
