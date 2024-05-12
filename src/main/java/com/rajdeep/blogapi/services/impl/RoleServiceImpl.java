package com.rajdeep.blogapi.services.impl;

import com.rajdeep.blogapi.daoRepositories.RoleDao;
import com.rajdeep.blogapi.daoRepositories.UserDao;
import com.rajdeep.blogapi.exceptionsHandlers.ResourceNotFoundException;
import com.rajdeep.blogapi.modelEntity.RoleModel;
import com.rajdeep.blogapi.modelEntity.UserModel;
import com.rajdeep.blogapi.payloadData.RolePayload;
import com.rajdeep.blogapi.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;


    @Autowired
    ModelMapper modelMapper;

    @Override
    public RolePayload addRole(RolePayload rolePayload) {

        RoleModel roleModel= this.modelMapper.map(rolePayload,RoleModel.class);

        RoleModel savedRole= this.roleDao.save(roleModel);

        return this.modelMapper.map(savedRole,RolePayload.class);

    }

    @Override
    public void deleteRole(Integer roleId) {

        this.roleDao.deleteById(roleId);

    }

    @Override
    public void assignRoleToUser(Integer roleId, Integer userId) {

        RoleModel roleModel= this.roleDao.findById(roleId).orElseThrow(()->new ResourceNotFoundException("Role","RoleId",roleId));

        UserModel userModel=this.userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User Id",userId));

        Set<RoleModel> roleSt=userModel.getRoles();

        roleSt.add(roleModel);

        userModel.setRoles(roleSt);

        UserModel saved= this.userDao.save(userModel);

    }

    @Override
    public List<RolePayload> findAllRoles() {

          List<RoleModel> roles= this.roleDao.findAll();

          List<RolePayload> rolePayloads=new ArrayList<>();

          for(RoleModel r:roles){
              rolePayloads.add(this.modelMapper.map(r,RolePayload.class));
          }
          return rolePayloads;
    }

    @Override
    public List<RolePayload> getRoleByUserName(String name) {

        // getting RoleModel by UserName

        List<RoleModel> roles = this.userDao.findRolesByName(name);

        List<RolePayload> rolePayloads=new ArrayList<>();

        for (RoleModel r:roles){
            rolePayloads.add(modelMapper.map(r,RolePayload.class));
        }

        return rolePayloads;

    }


}
