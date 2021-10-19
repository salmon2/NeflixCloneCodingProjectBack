package com.sparta.NeflixCloneCodingProjectBack.scheduled;

import com.sparta.NeflixCloneCodingProjectBack.MovieApi.MovieGenre;
import com.sparta.NeflixCloneCodingProjectBack.MovieApi.MovieSearchApi;
import com.sparta.NeflixCloneCodingProjectBack.dto.themovieapibygenredto.TheMovieApiByGenreResponseDto;
import com.sparta.NeflixCloneCodingProjectBack.dto.themovieapibygenredto.TheMovieApiResponseResultList;
import com.sparta.NeflixCloneCodingProjectBack.dto.themovieapibyiddto.TheMovieApiByIdResponseDto;
import com.sparta.NeflixCloneCodingProjectBack.dto.themovieapibyiddto.VideoListDto;
import com.sparta.NeflixCloneCodingProjectBack.dto.themovieapibyiddto.VideoListResult;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class ScheduleTest {
    private final MovieSearchApi movieSearchApi;
    private static int count = 0;

    @Scheduled(fixedDelay = 10000) // scheduler 끝나는 시간 기준으로 1000 간격으로 실행
    public void scheduleFixedDelayTask() throws Exception {
        count = count +1;
        System.out.println(count + "번째 스케쥴링 시작");
        TheMovieApiByGenreResponseDto theMovieApiResponseDto = movieSearchApi.TheMovieDBSearchByGenre(MovieGenre.Action);
        List<TheMovieApiResponseResultList> results = theMovieApiResponseDto.getResults();

        for (TheMovieApiResponseResultList responseDto: results) {
            String title = responseDto.getTitle();
            Long id = responseDto.getId();
            TheMovieApiByIdResponseDto theMovieApiByIdResponseDto = movieSearchApi.TheMovieDBSearchById(id);
            VideoListResult[] videoList = theMovieApiByIdResponseDto.getVideos().getResults();
            for (VideoListResult videoListResult : videoList) {
                System.out.println("videoListResult.getName() = " + videoListResult.getName());
            }
        }

    }

}
