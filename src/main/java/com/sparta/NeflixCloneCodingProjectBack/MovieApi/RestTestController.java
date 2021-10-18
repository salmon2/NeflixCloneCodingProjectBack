package com.sparta.NeflixCloneCodingProjectBack.MovieApi;

import com.sparta.NeflixCloneCodingProjectBack.dto.TheMovieApiResponseDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestTestController {

    @RequestMapping("/restTest")
    public TheMovieApiResponseDto restTest() throws Exception {

        MovieSearchApi movieSearchApi = new MovieSearchApi();
        TheMovieApiResponseDto theMovieApiResponseDto = movieSearchApi.TheMovieDBSearchByGenre(MovieGenreId.Mystery.getValue());

        return theMovieApiResponseDto;
    }


}
