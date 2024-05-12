package com.rajdeep.blogapi.services.impl;

import com.rajdeep.blogapi.config.AppConstants;
import com.rajdeep.blogapi.daoRepositories.RoleDao;
import com.rajdeep.blogapi.daoRepositories.UserDao;
import com.rajdeep.blogapi.exceptionsHandlers.ResourceNotFoundException;
import com.rajdeep.blogapi.modelEntity.RoleModel;
import com.rajdeep.blogapi.modelEntity.UserModel;
import com.rajdeep.blogapi.payloadData.RolePayload;
import com.rajdeep.blogapi.payloadData.UserPayload;
import com.rajdeep.blogapi.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ModelMapper modelMapper;
    // iss wali autowriring se, spring ModelMapper ki bean ko detect karega (declared in BlogApiApplication)
    // aur vha se ek ModelMapper ka object return karega
    // uss object ki lifecycle ko spring manage karega


    @Override
    public UserPayload createUser(UserPayload userPayload) {

       UserModel user = payloadToUserModel(userPayload);

       UserModel Saveduser= userDao.save(user);

       return userModelToPayload(Saveduser);

    }

    @Override
    public UserPayload updateUser(UserPayload userPayload, Integer userId) {

        UserModel user = userDao.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","id",userId));

        user.setName(userPayload.getName());
        user.setEmail(userPayload.getEmail());
        user.setPassword(userPayload.getPassword());
        user.setAbout(userPayload.getAbout());

        UserModel updatedUser=userDao.save(user);

        return userModelToPayload(user);

    }

    @Override
    public UserPayload getUserById(Integer userId) {

        UserModel user = userDao.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","id",userId));

        return userModelToPayload(user);

    }

    @Override
    public List<UserPayload> getAllUsers() {

        List<UserModel> users= userDao.findAll();

        List<UserPayload> user1=new ArrayList<>();

        for(UserModel u:users){
            UserPayload u1=userModelToPayload(u);
            user1.add(u1);
        }

        return user1;
    }

    @Override
    public void deleteUser(Integer userId) {

        UserModel user=userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","Id",userId));

        userDao.delete(user);

        return;
    }


    // converting payload object to UserModel object (using manual method)

    // but we can do it simply using an in-build ModelMapper library because as soon as
    // types of objects increases it will be difficult task to write manual methods to convert object of one type to another type

    private UserModel payloadToUserModel(UserPayload userPayload){

//        UserModel user= new UserModel();
//        user.setId(userPayload.getId());
//        user.setName(userPayload.getName());
//        user.setEmail(userPayload.getEmail());
//        user.setAbout(userPayload.getAbout());
//        user.setPassword(userPayload.getPassword());
//        return user;

        UserModel user = this.modelMapper.map(userPayload,UserModel.class);

        return user;
    }


    // converting userModel object to Payload object.

    public UserPayload userModelToPayload(UserModel user){

//        UserPayload userPayload= new UserPayload();
//
//        userPayload.setId(user.getId());
//        userPayload.setName(user.getName());
//        userPayload.setEmail(user.getEmail());
//        userPayload.setAbout(user.getAbout());
//        userPayload.setPassword(user.getPassword());
//
//        return userPayload;

        UserPayload userPayload = this.modelMapper.map(user, UserPayload.class);

        return userPayload;


    }

    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    RoleDao roleDao;

    @Override
    public UserPayload registerNewUser(UserPayload user) {

          UserModel userModel= this.modelMapper.map(user, UserModel.class);

          // encode the password

        userModel.setPassword(this.passwordEncoder.encode(userModel.getPassword()));

        // finding default role (let default role role Id is NORMAL_USER id= 808)

        // .get() to avoid orElseThrow, because we are sure that this role id is present in DB

        RoleModel roleModel=this.roleDao.findById(AppConstants.NORMAL_USER).get();

        // giving default role to the user

        userModel.getRoles().add(roleModel);

        UserModel updateUser=this.userDao.save(userModel);

        return this.modelMapper.map(updateUser,UserPayload.class);

    }





}
