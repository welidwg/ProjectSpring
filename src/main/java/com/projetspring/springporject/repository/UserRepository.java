package com.projetspring.springporject.repository;

import com.projetspring.springporject.entity.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<AppUser,String> {
    public AppUser findAppUserByUsername(String username);
    public AppUser findAppUserById(String id);
    public List<AppUser> findAppUserByUsernameContaining(String mc);
    public Page<AppUser> findAppUserByUsernameContaining(String mc, Pageable p);

}
