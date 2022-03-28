package com.example.fleet.websecurity;

import com.example.fleet.model.Client;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MyPasswordEncoder implements PasswordEncoder {

    private Client client;

    public MyPasswordEncoder(Client client) {
        this.client = client;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return null;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return false;
    }
}
