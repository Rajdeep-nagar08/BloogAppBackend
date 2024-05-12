package com.rajdeep.blogapi.modelEntity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@Data

public class UserModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_name",nullable = false,length = 100)
    private  String name;
    private String email;
    private String password;
    private String about;


//     one user can have multiple posts

    @OneToMany(mappedBy = "userMapped", cascade = CascadeType.ALL)
    private List<PostModel> posts= new ArrayList<>();


    // one user can have multiple roles(OneToMany)
    // and multiple users can have one role(ManyToOne) == ManyToMany
    // making new third table "user_roles", which contains two column : userId (may be user_id)
    // , roleId (may be role_id)

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name="user_roles",
            joinColumns=@JoinColumn(name = "userId",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="roleId",referencedColumnName = "id"))
    private Set<RoleModel>roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        // grantedAuthority of user is their roles

        // so we convert each role to GrantedAuthority and return

        List<GrantedAuthority> listRoles=new ArrayList<>();

        for(RoleModel r:roles){
            listRoles.add(new SimpleGrantedAuthority(r.getName()));
        }


        return listRoles;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}