package com.projetspring.springporject.service;

import com.projetspring.springporject.entity.AppUser;
import com.projetspring.springporject.entity.Task;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IServiceUser {
    public void saveUser(AppUser user, MultipartFile file);
    public List<AppUser> getUser();
    public AppUser getUserById(Long id);

    public AppUser getUserByUsername(String username);

    public void deleteUser(Long id);
}
