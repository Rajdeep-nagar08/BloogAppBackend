package com.rajdeep.blogapi.payloadData;


import lombok.*;


@Data

public class CommentPayload {

    private Integer id;

    private String content;

    PostPayload post;

}
