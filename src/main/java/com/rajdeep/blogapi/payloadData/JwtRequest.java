package com.rajdeep.blogapi.payloadData;


import lombok.*;

@Data
@Builder
@ToString
public class JwtRequest {

    private String username;

    private String password;

}
