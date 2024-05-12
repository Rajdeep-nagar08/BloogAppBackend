package com.rajdeep.blogapi.modelEntity;


import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="categories")
@NoArgsConstructor
@Getter
@Setter
@Data
public class CategoryModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @Column(name="title",length = 100, nullable = false)
    private String categoryTitle;

    @Column(name="description")
    private String categoryDescription;


    // one category belongs to multiple posts, so storing all its related posts in a list


    // cascade = CasecadeType.ALL (if parent is removed then child also removed, If parent is saved then its child automatically saved
    // fetch = FetechType.LAZY (if we wants that child not removed if parent is removed) etc.


    @OneToMany(mappedBy = "categoryMapped", cascade = CascadeType.ALL)
    private List<PostModel> posts= new ArrayList<>();


}
