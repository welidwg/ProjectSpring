package com.projetspring.springporject.request;

import com.projetspring.springporject.entity.AppUser;
import lombok.Data;

import java.util.List;

@Data
public class AffectTaskRequest {
    private Long id;
    private List<AppUser> users;
}
