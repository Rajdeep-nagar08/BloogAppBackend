
package com.rajdeep.blogapi.modelEntity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="posts")
@Getter
@Setter
@NoArgsConstructor
public class PostModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(name="post_title", nullable = false)
    private String title;

    private String content;

    private String imageName;

    private Date addedDate;

    // to which category this post belongs to ?


    // many posts can have same category

    @ManyToOne
    @JoinColumn(name="category_mapped")
    private CategoryModel categoryMapped;

    // which user create this post ?


    // multiple posts can belongs to one user

    @ManyToOne
    @JoinColumn(name="user_mapped")
    private UserModel userMapped;


    // one post can have multiple comments

    @OneToMany(mappedBy = "postMapped",cascade = CascadeType.ALL)
    private List<CommentModel> commentMapped=new ArrayList<>();


}