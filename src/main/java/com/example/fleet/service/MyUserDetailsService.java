package com.example.fleet.service;

import com.example.fleet.model.Client;
import com.example.fleet.repository.ClientRepository;
import com.example.fleet.websecurity.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Client client = clientRepository.findByEmailAddress(email);

        return new MyUserDetails(client);
    }
}
