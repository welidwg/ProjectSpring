package com.projetspring.springporject.repository;

import com.projetspring.springporject.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser,Long> {
    public AppUser findAppUserByUsername(String username);
}
