package com.rajdeep.blogapi.payloadData;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
public class PostPayload {

    private Integer postId;

    private String title;

    private String content;

    private String imageName;

    private Date addedDate;

    private CategoryPayload categoryMapped;

    private UserPayload userMapped;

//    List<CommentPayload> commentMapped;

}