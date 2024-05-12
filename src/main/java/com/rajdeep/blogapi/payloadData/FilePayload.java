package com.rajdeep.blogapi.payloadData;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data

public class FilePayload {

    private String fileName;

    private String message;


}
