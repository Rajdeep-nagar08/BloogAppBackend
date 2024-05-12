package com.rajdeep.blogapi.daoRepositories;


import com.rajdeep.blogapi.modelEntity.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface RoleDao extends JpaRepository<RoleModel,Integer> {

    RoleModel findByName(String Name);


}
