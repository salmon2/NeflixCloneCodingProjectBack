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
    private String largeCategoryName;

    @OneToMany(mappedBy = "largeCategory", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Video> videoList = new ArrayList<>();


    public LargeCategory(String largeCategoryName) {
        this.largeCategoryName = largeCategoryName;
    }
}
