package com.projetspring.springporject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Status {
    @Id
    private Long id;

    private String label;

    private String className;
}
