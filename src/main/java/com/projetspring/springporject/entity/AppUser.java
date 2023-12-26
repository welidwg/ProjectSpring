package com.projetspring.springporject.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class AppUser {
    @Id
    private String id;
    @Column(unique = true)
    @NotNull(message = "The username is required")
    @NotEmpty(message = "The username is required")
    private String username;
    @NotNull(message = "The password is required")
    @NotEmpty(message = "The password is required")
    //@Min(value = 8,message = "The password must contain at least 8 characters")
    private String password;
    @Column(unique = true)
    @NotNull(message = "The email is required")
    @NotEmpty(message = "The email is required")
    private String email;
    @Nullable
    private String avatar;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.DETACH)
    private List<AppRole> roles;


    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
    private List<TaskAssignment> assignments;

}
