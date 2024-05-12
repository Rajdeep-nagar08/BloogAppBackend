package com.rajdeep.blogapi.modelEntity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comments")
@Getter
@Setter
@Data
@NoArgsConstructor
public class CommentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    private String content;

    // to which post this comment belongs to

    // multiple comments belongs to this one post
//
    @ManyToOne
    @JoinColumn(name = "post_mapped")
    private PostModel postMapped;

}
