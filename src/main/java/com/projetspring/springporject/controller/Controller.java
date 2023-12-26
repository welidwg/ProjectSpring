package com.projetspring.springporject.controller;

import com.projetspring.springporject.entity.AppUser;
import com.projetspring.springporject.service.ServiceAccount;
import com.pusher.rest.Pusher;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class Controller {
    ServiceAccount serviceAccount;
    @Autowired
    Pusher pusher;

    @GetMapping("/user/index")
    public String index(){
        return "hello";
    }

    @GetMapping("/errorPage")
    public String errorPage(){
        return "error";
    }
    @GetMapping("/pusher")
    public String pusher(){
        pusher.trigger("notif","notif-event","Hello from spring boot");
        return "pusher";
    }

    @GetMapping("/successLogin")
    public String success (){
        return "success";
    }

}
