package com.sparta.NeflixCloneCodingProjectBack.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SmallCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String smallCategoryName;

    @Column(nullable = false)
    private int smallCategoryNumber;

    @OneToMany(mappedBy = "smallCategory", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<VideoSmallCategory> videoSmallCategoryList = new ArrayList<>();

    public SmallCategory(String smallCategory, int smallCategoryNumber) {
        this.smallCategoryName = smallCategory;
        this.smallCategoryNumber = smallCategoryNumber;
    }
}
