package com.balestech.security.controller;

import com.balestech.security.entity.Authority;
import com.balestech.security.entity.User;
import com.balestech.security.model.UserModel;
import com.balestech.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccessController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/register")
    public User register(@RequestBody UserModel userModel) {
        User user = new User();
        user.setEmail(userModel.getEmail());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        user.setAuthorities(userModel.getAuthorities());
        return userRepository.save(user);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<HttpStatus> login(@RequestBody UserModel userModel) throws Exception {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userModel.getEmail(), userModel.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid Credentials");
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }

    @GetMapping(value = "/profile")
    public String profile() throws Exception {
        return "Profile Page";
    }
}
