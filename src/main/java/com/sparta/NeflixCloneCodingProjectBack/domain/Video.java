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
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Boolean adult;

    @Column
    private String backdrop_path;

    @Column(nullable = false)
    private String title;

    @Column
    private String original_language;

    @Column
    private String original_title;

    @Column
    private String overview;

    @Column
    private Float popularity;

    @Column
    private String poster_path;

    @Column
    private String release_date;

    @Column
    private Boolean video;

    @Column
    private Float vote_average;

    @Column
    private Long vote_count;

    @OneToMany(mappedBy = "video",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<VideoLargeCategory> videoLargeCategories = new ArrayList<>();

}
