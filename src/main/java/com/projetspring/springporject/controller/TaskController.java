package com.projetspring.springporject.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.projetspring.springporject.entity.AppUser;
import com.projetspring.springporject.entity.Status;
import com.projetspring.springporject.entity.Task;
import com.projetspring.springporject.request.AffectTaskRequest;
import com.projetspring.springporject.service.ServiceAccount;
import com.projetspring.springporject.service.ServiceStatus;
import com.projetspring.springporject.service.ServiceTask;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.support.RepositoryConstraintViolationExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class TaskController {
    @Autowired
    ServiceTask serviceTask;
    @Autowired
    ServiceAccount serviceAccount;

    @PostMapping("/task/add")
    public ResponseEntity<?> addTask(@RequestBody @Valid Task t, BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            serviceTask.saveTask(t);
            return new ResponseEntity<>(serviceTask.getTasks(), HttpStatus.OK);
        }
        else{
            List<String> errors = new ArrayList<>();
            for(FieldError error : bindingResult.getFieldErrors()){
                String errorMessage = String.format("%s",error.getDefaultMessage());
                errors.add(String.format(errorMessage));
                System.out.println("Validation error: " + errorMessage);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

    }
    @GetMapping("/task/getAll")
    public Page<Task> getAll(@RequestParam(defaultValue = "") String mc, @RequestParam(name = "page", defaultValue = "0") int page,
                             @RequestParam(name = "size", defaultValue = "3") int size){
        return serviceTask.getTaskByMC(mc, PageRequest.of(page,size));
    }
    @GetMapping("/task/all")
    public List<Task> getAllNoPaginate(@RequestParam(defaultValue = "") String mc){
        return serviceTask.getTaskByMC(mc);
    }
    @DeleteMapping("/task/delete/{id}")
    public String delete(@PathVariable Long id){
        serviceTask.deleteTask(id);
        return "done";
    }
    @PostMapping("/task/edit")
    public String editTask(@RequestBody Task t){
        serviceTask.saveTask(t);
        return "done";

    }
//    @PostMapping("/task/affect")
//    public String addTaskToUser(@RequestBody Task t){
//         t.getUsers().forEach(user->serviceTask.addTaskToUser(user.getId(),t.getId()));
//        return t.getUsers().toString();
//    }

//    @PutMapping("/task/status/{idTask}")
//    public String updateStatus(@RequestBody Status status,@PathVariable Long idTask){
//        serviceTask.UpdateStatus(status.getId(),idTask);
//        return "done";
//    }
}
