package com.projetspring.springporject.repository;

import com.projetspring.springporject.entity.AppRole;
import com.projetspring.springporject.entity.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaRepository<AppUser,String> {
    public AppUser findAppUserByUsername(String username);
    public AppUser findAppUserById(String id);
    public List<AppUser> findAppUserByUsernameContaining(String mc);
    public Page<AppUser> findAppUserByUsernameContaining(String mc, Pageable p);
    public List<AppUser> findAppUserByRolesIn(Set<AppRole> roles);

}
