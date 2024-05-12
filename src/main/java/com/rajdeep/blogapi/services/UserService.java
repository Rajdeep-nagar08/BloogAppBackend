package com.rajdeep.blogapi.services;

import com.rajdeep.blogapi.payloadData.UserPayload;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@SpringBootApplication
public interface UserService {

    UserPayload createUser(UserPayload users);

    UserPayload updateUser(UserPayload user, Integer userId);

    UserPayload getUserById(Integer userId);

    List<UserPayload> getAllUsers();

    void deleteUser(Integer userId);

    UserPayload registerNewUser(UserPayload user);


}
