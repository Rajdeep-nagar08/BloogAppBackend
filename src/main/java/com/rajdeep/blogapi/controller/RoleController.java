package com.rajdeep.blogapi.controller;


import com.rajdeep.blogapi.modelEntity.RoleModel;
import com.rajdeep.blogapi.payloadData.ApiResponse;
import com.rajdeep.blogapi.payloadData.RolePayload;
import com.rajdeep.blogapi.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PostAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController

@RequestMapping("/role")
public class RoleController {

    // add role

    @Autowired
    RoleService roleService;


    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/add")
    public ResponseEntity<RolePayload> addRole(@RequestBody RolePayload rolePayload){

        RolePayload savedRole= this.roleService.addRole(rolePayload);

        return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
    }


    // delete role

    @DeleteMapping("/delete/{roleId}")
    public ResponseEntity<?> deleteRole(@PathVariable Integer roleId){

        this.roleService.deleteRole(roleId);

        return new ResponseEntity<>(new ApiResponse("Role Deleted !!!",true),HttpStatus.OK);
    }

    // assign role to user

    @PostMapping("/{roleId}/user/{userId}")
    public ResponseEntity<?> assignRoleToUser(@PathVariable Integer roleId, @PathVariable Integer userId){

     this.roleService.assignRoleToUser(roleId,userId);

     return new ResponseEntity<>(new ApiResponse("RoleID "+roleId+ " assigned to userId "+userId,true),HttpStatus.OK);

    }

    @GetMapping("/getAllRoles")

    public ResponseEntity<List<RolePayload>> findAllRoles(){

        List<RolePayload> roles= this.roleService.findAllRoles();

        return new ResponseEntity<>(roles,HttpStatus.OK);

    }


    // get role by user name e.g Rajdeep => Admin

    @GetMapping("/get/{name}")

    public ResponseEntity<List<RolePayload>> findRoleByUserName(@PathVariable String name){

        List<RolePayload> rolePayloads= this.roleService.getRoleByUserName(name);

        return new ResponseEntity<>(rolePayloads,HttpStatus.OK);

    }


}
