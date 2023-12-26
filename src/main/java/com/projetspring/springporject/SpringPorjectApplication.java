package com.projetspring.springporject;

import com.projetspring.springporject.entity.AppRole;
import com.projetspring.springporject.entity.AppUser;
import com.projetspring.springporject.service.IServiceAccount;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class SpringPorjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPorjectApplication.class, args);
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    CommandLineRunner commandLineRunner(IServiceAccount accountService) {
        return args -> {

//			accountService.addRole("USER");
//		accountService.addRole("ADMIN");
//            List<AppRole> roles =new ArrayList<>();
//            roles.add(accountService.getAppRole("ADMIN"));
//            roles.add(accountService.getAppRole("USER"));
//        AppUser user=AppUser.builder().id(UUID.randomUUID().toString()).username("admin").email("admin@gm.com").password("11223344").roles(roles).build();
//        accountService.addUser(user);

        };
    }
}
