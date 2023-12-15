package com.projetspring.springporject.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Task {
    @Id
    private Long id;
    private String title;
    private String description;
    private Date due_date;
    @ManyToOne
    private Status status;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<AppUser> users;


}
