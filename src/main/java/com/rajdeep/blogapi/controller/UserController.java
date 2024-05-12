package com.rajdeep.blogapi.controller;

import com.rajdeep.blogapi.payloadData.ApiResponse;
import com.rajdeep.blogapi.payloadData.UserPayload;
import com.rajdeep.blogapi.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    private UserService userService;

    // create User

    // @Valid annotation to enable validations that we apply on payload
    @PostMapping("/")
    public ResponseEntity<UserPayload> createUser(@Valid @RequestBody UserPayload userPayload){
        UserPayload createdUser=userService.createUser(userPayload);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // Put- update user

    @PutMapping("/{userId}")
    public ResponseEntity<UserPayload> updateUser(@Valid @RequestBody UserPayload userPayload,
                                                  @PathVariable("userId") Integer uid){
        UserPayload updatedUser= userService.updateUser(userPayload,uid);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // delete user

//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uId) {

            userService.deleteUser(uId);

            return new ResponseEntity<>(new ApiResponse("User Deleted Successfully",true), HttpStatus.OK);

    }


    // get user by id

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Integer uId){

             UserPayload user = userService.getUserById(uId);

             return new ResponseEntity<>(user,HttpStatus.OK);

    }

    // get all users

    @GetMapping("/all")

    public ResponseEntity<List<UserPayload>> getAllUsers(){

        List<UserPayload> allUsers=userService.getAllUsers();

        return new ResponseEntity<>(allUsers,HttpStatus.OK);
    }



}
