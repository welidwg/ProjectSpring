package com.projetspring.springporject.service;

import com.projetspring.springporject.entity.AppRole;
import com.projetspring.springporject.entity.AppUser;
import com.projetspring.springporject.repository.RoleRepository;
import com.projetspring.springporject.repository.TaskAssignmentRepository;
import com.projetspring.springporject.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class ServiceAccount implements IServiceAccount {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TaskAssignmentRepository serviceAssignment;

    @Autowired
    private EmailService emailService;

    private String saveImage(MultipartFile mf) throws IOException {
        String nom= mf.getOriginalFilename();
        String tab[]=nom.split("\\.");
        String newNAme=tab[0]+System.currentTimeMillis()+"."+tab[1];
        File f = new ClassPathResource("static/photos").getFile();
        String chemin=f.getAbsolutePath();
        Path p= Paths.get(chemin,newNAme);
        Files.write(p,mf.getBytes());
        return newNAme;
    }
    @Override
    public void addUser(AppUser user,MultipartFile mf) throws IOException, MessagingException {
        AppUser appUser=userRepository.findAppUserByUsername(user.getUsername());
        if (appUser != null)
            throw new RuntimeException("User does exist");
       AppUser created = userRepository.save(AppUser.builder().
               id(UUID.randomUUID().toString())
               .username(user.getUsername())
               .email(user.getEmail())
               .password(passwordEncoder.encode(user.getPassword()))
               .roles(user.getRoles()).build());
        if(mf !=null && !mf.isEmpty()){
            created.setAvatar(saveImage(mf));
        }
        emailService.sendEmail(user.getEmail(), "Account created in TaskManager", "Hello  <strong>"+user.getUsername()+"</strong> <br/> " +
                "An administrator have successfully created an account for you in the web platform <strong>Task Manager</strong> <br/> " +
                "Your login credentials are : <br/><br/>" +
                "<strong>Username :</strong> "+user.getUsername()+"<br/>" +
                "<strong>Password :</strong> "+user.getPassword());


    }

    @Override
    public void editUser(AppUser user, MultipartFile mf) throws IOException {
        AppUser appUser=userRepository.findAppUserById(user.getId());
        if (appUser == null)
            throw new RuntimeException("User does not exist");
        if(user.getPassword().isEmpty()){
            user.setPassword(appUser.getPassword());
        }else{
            if(!user.getPassword().equals(appUser.getPassword()))
                 user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
        if(mf!=null && !mf.isEmpty()){
            appUser.setAvatar(saveImage(mf));
        }
    }

    @Override
    public void addRole(String nom) {
        AppRole appRole=roleRepository.findById(nom).orElse(null);
        if(appRole!=null)
            throw new RuntimeException("Role does exist");
        roleRepository.save(AppRole.builder().role(nom).build());


    }

    @Override
    public void addRoleToUser(String username, String nameRole) {
        try {
            AppUser user = userRepository.findAppUserByUsername(username);
            AppRole role = roleRepository.findById(nameRole).get();
            user.getRoles().add(role);
        }catch (NullPointerException e){
            System.out.println("error add role to user");
        }

    }

    @Override
    public void deleteRoleToUser(String username, String nameRole) {
        AppUser user=userRepository.findAppUserByUsername(username);
        AppRole role=roleRepository.findById(nameRole).get();
        user.getRoles().remove(role);
    }

    @Override
    public void deleteUser(String id) {
        AppUser user=userRepository.findAppUserById(id);
        if(serviceAssignment.existsByUser(user)){
            throw new RuntimeException("This user is already assigned to a task ! It cant be deleted");
        }
        userRepository.deleteById(id);
    }

    @Override
    public AppUser getAppuser(String username) {
        return userRepository.findAppUserByUsername(username);
    }

    @Override
    public List<AppRole> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public List<AppUser> getAllAppUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<AppUser> getAllUsersByRole(Set<AppRole> roles) {
        return userRepository.findAppUserByRolesIn(roles);
    }

    @Override
    public AppUser getAppUserById(String id) {
        return userRepository.findAppUserById(id);
    }

    @Override
    public List<AppUser> getAppUserByMC(String mc) {
        return userRepository.findAppUserByUsernameContaining(mc);
    }

    @Override
    public Page<AppUser> getAppUserByMC(String mc, Pageable p) {
        return userRepository.findAppUserByUsernameContaining(mc,p);
    }

    @Override
    public AppRole getAppRole(String role) {
        return roleRepository.findById(role).orElse(null);
    }
}
