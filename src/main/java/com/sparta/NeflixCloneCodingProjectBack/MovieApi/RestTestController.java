package com.sparta.NeflixCloneCodingProjectBack.MovieApi;

import com.sparta.NeflixCloneCodingProjectBack.dto.themovieapibygenredto.TheMovieApiByGenreResponseDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestTestController {

    @RequestMapping("/restTest")
    public TheMovieApiByGenreResponseDto restTest() throws Exception {

        MovieSearchApi movieSearchApi = new MovieSearchApi();
        TheMovieApiByGenreResponseDto theMovieApiResponseDto = movieSearchApi.TheMovieDBSearchByGenre(MovieGenre.Mystery);

        return theMovieApiResponseDto;
    }


}
