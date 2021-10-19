package com.sparta.NeflixCloneCodingProjectBack.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class VideoDto {
    private Boolean adult;
    private String backdrop_path;
    private String title;
    private String original_language;
    private String original_title;
    private String overview;
    private Float popularity;
    private String poster_path;
    private String release_date;
    private Boolean video;
    private Float vote_average;
    private Long vote_count;
    private List<Long> genre;

    public VideoDto(TheMovieApiResponseResultList list) {
        if (list.getTitle() != null) {
            this.title = list.getTitle();
        } else {
            this.title = "";
        }

        if (list.getOverview() != null) {
            this.overview = list.getOverview();
        } else {
            this.overview = "";
        }

        if (list.getOriginal_language() != null) {
            this.original_language = list.getOriginal_language();
        } else {
            this.original_language = "";
        }

        if (list.getOriginal_title() != null) {
            this.original_title = list.getOriginal_title();
        } else {
            this.original_title = "";
        }

        if (list.getBackdrop_path() != null) {
            this.backdrop_path = list.getBackdrop_path();
        } else {
            this.backdrop_path = "";
        }

        if (list.getPopularity() != null) {
            this.popularity = list.getPopularity();
        } else {
            this.popularity = 0F;
        }

        if (list.getPoster_path() != null) {
            this.poster_path = list.getPoster_path();
        } else {
            this.poster_path = "";
        }

        if (list.getRelease_date() != null) {
            this.release_date = list.getRelease_date();
        } else {
            this.release_date = "";
        }

        if (list.getVote_average() != null) {
            this.vote_average = list.getVote_average();
        } else {
            this.vote_average = 0F;
        }

        if (list.getVote_count() != null) {
            this.vote_count = list.getVote_count();
        } else {
            this.vote_count = 0L;
        }
    }
}
