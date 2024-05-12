package com.rajdeep.blogapi.services;

import com.rajdeep.blogapi.payloadData.RolePayload;

import java.util.List;
import java.util.Set;

public interface RoleService {

    // add new role

    RolePayload addRole(RolePayload rolePayload);


    // delete role

    void deleteRole(Integer roleId);


    // assign role to the user
    void assignRoleToUser(Integer roleId, Integer userId);


    // find all roles

    List<RolePayload> findAllRoles();



    List<RolePayload> getRoleByUserName(String name);

}
