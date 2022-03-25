package com.example.fleet.service;

import com.example.fleet.model.Client;
import com.example.fleet.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Client client = clientRepository.findByEmailAddress(email);

        UserDetails user = User.withDefaultPasswordEncoder()
                .username(client.getEmailAddress())
                .password(client.getPassword())
                .build();

        return user;
    }
}
