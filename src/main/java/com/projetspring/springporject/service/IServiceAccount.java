package com.projetspring.springporject.service;

import com.projetspring.springporject.entity.AppRole;
import com.projetspring.springporject.entity.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IServiceAccount {
    public void addUser(AppUser user, MultipartFile mf) throws IOException;
    public void editUser(AppUser user,MultipartFile mf) throws IOException;
    public  void addRole(String nom);
    public void addRoleToUser(String username,String nameRole);

    public void deleteRoleToUser(String username,String nameRole);

    public void deleteUser(String id);
    public AppUser getAppuser(String username);
    public List<AppRole> getAllRoles();
    public List<AppUser> getAllAppUsers();
    public AppUser getAppUserById(String id);
    public List<AppUser> getAppUserByMC(String mc);
    public Page<AppUser> getAppUserByMC(String mc, Pageable p);
    public AppRole getAppRole(String role);
}
