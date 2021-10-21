package com.sparta.NeflixCloneCodingProjectBack.MovieApi;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MovieGenre {
    Action(28, "Action"), Adventure(12, "Adventure"),
    Fantasy(14, "Fantasy"), History(36, "History"), Horror(27, "Horror"),
    Music(10402, "Music"), Romance(10749, "Romance"),
    ScienceFiction(878, "ScienceFiction"),
    TVMovie(10770, "TVMovie"), Thriller(53, "Thriller"),
    War(10752, "War");

    private final int value;
    private final String GenreName;

}
