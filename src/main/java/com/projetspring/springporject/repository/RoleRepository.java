package com.projetspring.springporject.repository;

import com.projetspring.springporject.entity.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<AppRole,String> {
}
