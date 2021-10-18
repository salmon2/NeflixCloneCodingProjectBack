package com.sparta.NeflixCloneCodingProjectBack.video;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.NeflixCloneCodingProjectBack.largecategory.LargeCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String videoUrl;

    @Column(nullable = false)
    private String overview;

    @Column(nullable = false)
    private String first_date;

    @Column(nullable = false)
    private String series;

    @Column(nullable = false)
    private String trailerUrl;

    @Column(nullable = false)
    private double grade;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "largecategoryId", nullable = false)
    private LargeCategory largeCategory;

}
