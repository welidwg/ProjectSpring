package com.projetspring.springporject.entity;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull(message = "The title is required")
     @NotEmpty(message = "The title is required")
    private String title;
    @NotNull(message = "The description is required")
    @NotEmpty(message = "The description is required")
    private String description;
    @OneToMany(mappedBy = "task", cascade = CascadeType.REMOVE,orphanRemoval = true)
    @JsonIdentityReference(alwaysAsId = true)
    private List<TaskAssignment> assignments;


}
