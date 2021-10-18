package com.sparta.NeflixCloneCodingProjectBack.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TheMovieApiResponseResultList {
    private Boolean adult;
    private String backdrop_path;
    private Long[] genre_ids;
    private Long id;
    private String original_language;
    private String original_title;
    private String overview;
    private Float popularity;
    private String poster_path;
    private String release_date;
    private String title;
    private Boolean video;
    private Float vote_average;
    private Long vote_count;
}
