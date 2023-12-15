package com.projetspring.springporject.service;

import com.projetspring.springporject.entity.AppRole;
import com.projetspring.springporject.entity.AppUser;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IServiceRole {
    public void saveRole(AppUser user, MultipartFile file);
    public List<AppRole> getRole();
    public AppRole getRoleById(String role);
    public void deleteRole(String role);
}
