package com.sparta.NeflixCloneCodingProjectBack.MovieApi;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AllGenre {
    Action("Action"), Animation("Animation"),
    Comedy("Comedy"), Crime("Crime"), Documentary("Documentary"), Drama("Drama"),
    Family("Family"),Mystery("Mystery"),
    ScienceFiction("ScienceFiction"), War("War"), Western("Western");


    private final String GenreName;

}
