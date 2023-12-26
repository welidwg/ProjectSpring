package com.projetspring.springporject.controller;

import com.projetspring.springporject.entity.AppRole;
import com.projetspring.springporject.entity.AppUser;
import com.projetspring.springporject.request.AppUserRequest;
import com.projetspring.springporject.service.ServiceAccount;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@NoArgsConstructor
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class AccountController {

    @Autowired
    ServiceAccount serviceAccount;
    @PostMapping(value = "/addUser", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addUser(@RequestPart(value = "data") @Valid AppUser user, BindingResult bindingResult, @RequestPart(value = "photo", required = false) MultipartFile mf) throws IOException {
        if (!bindingResult.hasErrors()) {
            serviceAccount.addUser(user, mf);
            return new ResponseEntity<>(serviceAccount.getAllAppUsers(), HttpStatus.OK);
        } else {
            List<String> errors = new ArrayList<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                String errorMessage = String.format("%s", error.getDefaultMessage());
                errors.add(String.format(errorMessage));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
    }

    @PostMapping("/addRole")
    public String addRole(@RequestBody AppRole role) {
        serviceAccount.addRole(role.getRole());
        return "done";
    }

    @PutMapping("/account/edit")
    public String editUser(@RequestPart(value = "data") AppUser user, @RequestPart(value = "photo", required = false) MultipartFile mf) throws IOException {
        serviceAccount.editUser(user, mf);
        return "done";
    }

    @GetMapping("/account/getAll")
    public Page<AppUser> getAll(@RequestParam(defaultValue = "") String mc, @RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "3") int size) {
        return serviceAccount.getAppUserByMC(mc, PageRequest.of(page,size));
    }
    @GetMapping("/account/all")
    public List<AppUser> getAllNoPaginate(@RequestParam(defaultValue = "") String mc) {
        return serviceAccount.getAppUserByMC(mc);
    }
    @GetMapping("/roles/getAll")
    public List<AppRole> getAllRoles() {
        return serviceAccount.getAllRoles();
    }

    @GetMapping("/account/{username}")
    public AppUser getUser(@PathVariable String username) {
        return serviceAccount.getAppuser(username);
    }
    @GetMapping("/account/role/{role}")
    public List<AppUser> getUserByRole(@PathVariable String role) {
        Set<AppRole> list = new HashSet<>();
        list.add(new AppRole(role));
        return serviceAccount.getAllUsersByRole(list);
    }

    @DeleteMapping("/account/{id}/delete")
    public String deleteUser(@PathVariable String id) {
        serviceAccount.deleteUser(id);
        return "done";
    }

}
