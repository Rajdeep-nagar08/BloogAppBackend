package com.rajdeep.blogapi.daoRepositories;

import com.rajdeep.blogapi.modelEntity.RoleModel;
import com.rajdeep.blogapi.modelEntity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserDao extends JpaRepository<UserModel, Integer> {


    Optional<UserModel> findByEmail(String email);


    Optional<UserModel> findByName(String name);


    // getting role of a user by user's name

    @Query("Select u.roles From UserModel u where u.name = :name")
     List<RoleModel>  findRolesByName(String  name);

}

