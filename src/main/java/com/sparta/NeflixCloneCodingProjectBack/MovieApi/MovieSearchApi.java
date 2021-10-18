package com.sparta.NeflixCloneCodingProjectBack.MovieApi;

import com.fasterxml.jackson.databind.ObjectMapper;


import com.google.gson.Gson;
import com.sparta.NeflixCloneCodingProjectBack.dto.TheMovieApiResponseDto;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;


public class MovieSearchApi {
    private static final String api_key = "602df31a2288c091d05df37c8bf6ad7e";
    @Autowired
    private ObjectMapper objectMapper;

    public  TheMovieApiResponseDto TheMovieDBSearchByGenre(int genreId) throws Exception{
        HttpHeaders httpHeaders = makeHeaders();
        String genreStringId = Integer.toString(genreId);

        //Object Mapper를 통한 JSON 바인딩1
        HttpEntity httpEntity = new HttpEntity(httpHeaders);


        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity = restTemplate.exchange(
                "https://api.themoviedb.org/3/discover/movie?api_key="+api_key+
                        "&language=ko-KR&sort_by=popularity.desc&include_adult=false&include_video=false&page=1" +
                        "&primary_release_date.gte=2000-01-01&primary_release_date.lte=2100-12-31&vote_average.gte=6" +
                        "&with_genres="+genreStringId+"\n",
                HttpMethod.GET, httpEntity, String.class);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(responseEntity.getBody().toString());

        Gson gson = new Gson();
        TheMovieApiResponseDto theMovieApiResponseDto = gson.fromJson(jsonObject.toString(), TheMovieApiResponseDto.class);

        System.out.println("theMovieApiResponseDto = " + theMovieApiResponseDto.getResults());

        return theMovieApiResponseDto;
    }

    private static HttpHeaders makeHeaders() {
        //헤더 설정
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return httpHeaders;
    }

    public static void main(String[] args) throws Exception {
        MovieSearchApi movieSearchApi = new MovieSearchApi();
        movieSearchApi.TheMovieDBSearchByGenre(MovieGenreId.Mystery.getValue());


    }

}
