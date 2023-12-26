package com.projetspring.springporject.repository;

import com.projetspring.springporject.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status,Long> {
    public Status findStatusByLabel(String label);

}
