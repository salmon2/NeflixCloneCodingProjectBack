package com.sparta.NeflixCloneCodingProjectBack.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class VideoLargeCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "videoid")
    private Video video;

    @ManyToOne
    @JoinColumn(name = "largecategoryid")
    private LargeCategory largecategory;
}
