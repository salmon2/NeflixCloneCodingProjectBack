package com.sparta.NeflixCloneCodingProjectBack.dto.themovieapibygenredto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TheDramaApiResponseResultList {
    private String backdrop_path;
    private String first_air_date;
    private Long[] genre_ids;
    private Long id;
    private String name;
    private String[] origin_country;
    private String original_language;
    private String original_name;
    private String overview;
    private Float popularity;
    private String poster_path;
    private Float vote_average;
    private Long vote_count;
}
