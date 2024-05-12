package com.rajdeep.blogapi.modelEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Entity
@Table(name="roles")
@Getter
@Setter
@NoArgsConstructor
public class RoleModel {

    @Id
    private int id;

    private String name;

}
