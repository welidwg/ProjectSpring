package com.projetspring.springporject.service;

import com.projetspring.springporject.entity.AppUser;
import com.projetspring.springporject.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Lazy
    UserRepository serviceAccount;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser=serviceAccount.findAppUserByUsername(username);
        if(appUser==null) throw new UsernameNotFoundException("user not found");
        List<GrantedAuthority> authorities = new ArrayList<>();
        appUser.getRoles().forEach(e->authorities.add(new SimpleGrantedAuthority(e.getRole())));
        return new User(appUser.getUsername(),appUser.getPassword(),authorities);
    }
}
