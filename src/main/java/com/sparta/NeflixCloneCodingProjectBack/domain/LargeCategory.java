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
public class LargeCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String largecategory;

    @OneToMany(mappedBy = "largeCategory", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SmallCategory> smallCategories = new ArrayList<>();

    @OneToMany(mappedBy = "largecategory",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<VideoLargeCategory> videoLargeCategories = new ArrayList<>();
}
