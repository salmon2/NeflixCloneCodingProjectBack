package com.sparta.NeflixCloneCodingProjectBack.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.NeflixCloneCodingProjectBack.dto.themovieapibygenredto.TheMovieApiResponseResultList;
import com.sparta.NeflixCloneCodingProjectBack.dto.themovieapibyiddto.VideoListResult;
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
    public Video(TheMovieApiResponseResultList movie, VideoListResult youtube, LargeCategory largeCategory) {
        this.adult = movie.getAdult();
        this.title = movie.getTitle();
        this.original_language = movie.getOriginal_language();
        this.original_title = movie.getOriginal_title();
        this.overview = movie.getOverview();
        this.popularity = movie.getPopularity();
        this.posterPath = "https://image.tmdb.org/t/p/w500"+movie.getPoster_path();
        this.release_date = movie.getRelease_date();

        if(youtube == null)
            this.youtubePath = null;
        else
            this.youtubePath = "https://www.youtube.com/embed/"+youtube.getKey()+"?autoplay=1&mute=1";

        this.vote_average = movie.getVote_average();
        this.vote_count = movie.getVote_count();
        this.largeCategory = largeCategory;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Boolean adult;



    @Column(nullable = false)
    private String title;

    @Column
    private String original_language;

    @Column
    private String original_title;

    @Column( length = 100000 )
    private String overview;

    @Column
    private Float popularity;

    @Column
    private String posterPath;

    @Column
    private String release_date;

    @Column
    private String youtubePath;

    @Column
    private Float vote_average;

    @Column
    private Long vote_count;

    @OneToMany(mappedBy = "video",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<VideoSmallCategory> videoLargeCategories = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "largeCategoryId")
    private LargeCategory largeCategory;

}
