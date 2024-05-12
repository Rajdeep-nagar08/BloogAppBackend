package com.rajdeep.blogapi.payloadData;


import com.rajdeep.blogapi.modelEntity.RoleModel;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

// payload ===== exposed to user

// yha ham chizo ko kam jyda kar sakte h, sirf vhi

// chiiz likhenge jo ki hame UI (api) ki dikhani h from Users Database

@Data
public class UserPayload {
    private int id;

    @NotEmpty
    @Size(min =4, message = "Username must be min of 4 characters")
    private String name;

    @Email(message = "Email address is not valid")
    private String email;

    @NotEmpty
    @Size(min=3, max=10, message = "Password must be min of 3 chars and max of 4 chars")
//    @Pattern(regexp = )
    private String password;

    @NotEmpty
    private String about;

    private Set<RolePayload> roles=new HashSet<>();

}
