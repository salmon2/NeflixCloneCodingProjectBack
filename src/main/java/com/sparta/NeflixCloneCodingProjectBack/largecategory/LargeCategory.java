package com.sparta.NeflixCloneCodingProjectBack.largecategory;

import com.sparta.NeflixCloneCodingProjectBack.smallcategory.SmallCategory;
import com.sparta.NeflixCloneCodingProjectBack.video.Video;
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

    @OneToMany(mappedBy = "largeCategory", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Video> video = new ArrayList<>();
}
