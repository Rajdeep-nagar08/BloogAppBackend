package com.rajdeep.blogapi.controller;

import com.rajdeep.blogapi.JWT.JwtHelper;
import com.rajdeep.blogapi.JWT.JwtSecurityConfig;
import com.rajdeep.blogapi.JWT.UserDetailsConfig;
import com.rajdeep.blogapi.payloadData.JwtRequest;
import com.rajdeep.blogapi.payloadData.JwtResponse;
import com.rajdeep.blogapi.payloadData.UserPayload;
import com.rajdeep.blogapi.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {


    // to fetch user information (username, password, role etc.)
    // that is saved in UserDetailsService inside AppConfig
//    @Autowired
//    private AppConfig appConfig;


    @Autowired
    private UserDetailsConfig userConfig;

    // used to authenticate user
    @Autowired
    private AuthenticationManager manager;


    // to create Jwt
    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getUsername(), request.getPassword());

        UserDetails userDetails = userConfig.loadUserByUsername(request.getUsername());

        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        // authentication manager check user is valid or invalid (do authentication of user)
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }


    @Autowired
    UserService userService;

    @PostMapping("register/user")

    public ResponseEntity<UserPayload> registerUser(@RequestBody UserPayload user){

        UserPayload userPayload=this.userService.registerNewUser(user);

        return new ResponseEntity<>(userPayload, HttpStatus.CREATED);

    }



}