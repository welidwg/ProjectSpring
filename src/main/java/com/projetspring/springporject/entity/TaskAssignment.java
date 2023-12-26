package com.projetspring.springporject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class TaskAssignment {
    @Id
    private String id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "task_id")
    @JsonIgnoreProperties("assignments")
    private Task task;

    private Date dueDate;

    private Date assignemntDate;

    private Date finishedDate;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIgnoreProperties("assignments")
    private AppUser user;

    @ManyToOne
    private Status status;
}
