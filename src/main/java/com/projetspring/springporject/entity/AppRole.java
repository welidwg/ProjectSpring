package com.projetspring.springporject.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class AppRole {
    @Id
    private String role;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<AppUser> users;
}
