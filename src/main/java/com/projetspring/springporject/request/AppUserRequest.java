package com.projetspring.springporject.request;

import com.projetspring.springporject.entity.AppRole;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AppUserRequest {
    private String username;
    private String email;
    private String password;
    private List<AppRole> roles;

}
