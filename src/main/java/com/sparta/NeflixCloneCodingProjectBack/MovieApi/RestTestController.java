package com.sparta.NeflixCloneCodingProjectBack.MovieApi;

import com.sparta.NeflixCloneCodingProjectBack.dto.themovieapibyiddto.TheMovieApiByIdResponseDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestTestController {

    @RequestMapping("/restTest")
    public TheMovieApiByIdResponseDto restTest() throws Exception {

        MovieSearchApi movieSearchApi = new MovieSearchApi();
        //TheMovieApiByGenreResponseDto theMovieApiResponseDto = movieSearchApi.TheMovieDBSearchByGenre(MovieGenre.Mystery);
        TheMovieApiByIdResponseDto theMovieApiByIdResponseDto = movieSearchApi.TheMovieDBSearchById(631843L);

        return theMovieApiByIdResponseDto;
    }


}
