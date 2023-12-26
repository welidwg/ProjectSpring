package com.projetspring.springporject.security;


import com.pusher.rest.Pusher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PusherConfig {
    @Bean
    public Pusher pusher(){
        Pusher pusher = new Pusher("1442122", "33ae8c9470ab8fad0744", "465fb6577dcae37d36d2");
        pusher.setCluster("eu");
        return pusher;
    }
}
