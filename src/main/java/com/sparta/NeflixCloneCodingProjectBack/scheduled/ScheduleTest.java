package com.sparta.NeflixCloneCodingProjectBack.scheduled;

import com.sparta.NeflixCloneCodingProjectBack.MovieApi.MovieGenre;
import com.sparta.NeflixCloneCodingProjectBack.MovieApi.MovieSearchApi;
import com.sparta.NeflixCloneCodingProjectBack.domain.Video;
import com.sparta.NeflixCloneCodingProjectBack.dto.themovieapibygenredto.TheMovieApiByGenreResponseDto;
import com.sparta.NeflixCloneCodingProjectBack.dto.themovieapibygenredto.TheMovieApiResponseResultList;
import com.sparta.NeflixCloneCodingProjectBack.dto.themovieapibyiddto.TheMovieApiByIdResponseDto;
import com.sparta.NeflixCloneCodingProjectBack.dto.themovieapibyiddto.VideoListDto;
import com.sparta.NeflixCloneCodingProjectBack.dto.themovieapibyiddto.VideoListResult;
import com.sparta.NeflixCloneCodingProjectBack.video.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;


@Component
@RequiredArgsConstructor
public class ScheduleTest {
    private final MovieSearchApi movieSearchApi;
    private final VideoRepository videoRepository;
    private static int count = 0;

    @Scheduled(fixedDelay = 10000) // scheduler 끝나는 시간 기준으로 1000 간격으로 실행
    @Transactional
    public void scheduleFixedDelayTask() throws Exception {
        count = count +1;
        System.out.println(count + "번째 스케쥴링 시작");
        videoRepository.deleteAll();

        TheMovieApiByGenreResponseDto theMovieApiResponseDto = movieSearchApi.TheMovieDBSearchByGenre(MovieGenre.Action);
        List<TheMovieApiResponseResultList> results = theMovieApiResponseDto.getResults();

        for (TheMovieApiResponseResultList result : results) {

            Long id = result.getId();
            TheMovieApiByIdResponseDto theMovieApiByIdResponseDto = movieSearchApi.TheMovieDBSearchById(id);
            VideoListResult[] videoList = theMovieApiByIdResponseDto.getVideos().getResults();

            for (VideoListResult videoListResult : videoList) {
                if(videoListResult.getType().equals("Trailer")){
                    Video video = new Video(result, videoListResult);
                    videoRepository.save(video);

                    break;
                }
            }
        }


    }

}
