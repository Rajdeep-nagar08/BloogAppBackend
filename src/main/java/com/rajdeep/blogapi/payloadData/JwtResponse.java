package com.rajdeep.blogapi.payloadData;

import lombok.*;

@Data
@Builder
@ToString
public class JwtResponse {

    private String jwtToken;

    private String username;

}