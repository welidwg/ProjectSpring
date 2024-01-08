package com.projetspring.springporject.controller;

import com.projetspring.springporject.entity.Status;
import com.projetspring.springporject.service.ServiceStatus;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")


public class StatusController {
    @Autowired
    ServiceStatus serviceStatus;

    @PostMapping("/admin/status/add")
    public String addStatus(@RequestBody Status status){
        serviceStatus.saveStatus(status);
        return "done";
    }
    @GetMapping("/admin/status/getAll")
    public List<Status> getAll(){
        return serviceStatus.getStatuses();
    }

    @GetMapping("/status/label/{mc}")
    public Status getByLabel(@PathVariable String mc){
        return serviceStatus.getStatusByLabel(mc);
    }

    @DeleteMapping("/admin/status/{id}/delete")
    public String deleteStatus(@PathVariable Long id){
        serviceStatus.deleteStatus(id);
        return "done";
    }
}
