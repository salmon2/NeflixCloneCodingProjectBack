package com.sparta.NeflixCloneCodingProjectBack.controller;

import com.sparta.NeflixCloneCodingProjectBack.MovieApi.MovieGenre;
import com.sparta.NeflixCloneCodingProjectBack.MovieApi.MovieSearchApi;
import com.sparta.NeflixCloneCodingProjectBack.dto.ResponseDto;
import com.sparta.NeflixCloneCodingProjectBack.dto.themovieapibygenredto.TheMovieApiByGenreResponseDto;
import com.sparta.NeflixCloneCodingProjectBack.dto.themovieapibyiddto.TheMovieApiByIdResponseDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestTestController {
    private final String successMsg = "성공";
    private final String failMsg ="실패";

    @RequestMapping("/testId")
    public ResponseDto restTest() throws Exception {

        MovieSearchApi movieSearchApi = new MovieSearchApi();
        TheMovieApiByIdResponseDto theMovieApiByIdResponseDto = movieSearchApi.TheMovieDBSearchById(631843L);

        return new ResponseDto(500L, successMsg, theMovieApiByIdResponseDto);
    }

    @RequestMapping("/testGenre")
    public ResponseDto restTest2() throws Exception {

        MovieSearchApi movieSearchApi = new MovieSearchApi();
        TheMovieApiByGenreResponseDto theMovieApiResponseDto = movieSearchApi.TheMovieDBSearchByGenre(MovieGenre.Mystery);

        return new ResponseDto(500L, successMsg, theMovieApiResponseDto);

    }

    @RequestMapping("/test")
    public ResponseDto restTest3() throws Exception {

        return new ResponseDto(500L, successMsg, null);

    }


}
