package com.sparta.NeflixCloneCodingProjectBack.video;

import com.sparta.NeflixCloneCodingProjectBack.MovieApi.MovieGenreId;
import com.sparta.NeflixCloneCodingProjectBack.MovieApi.MovieSearchApi;
import com.sparta.NeflixCloneCodingProjectBack.domain.Video;
import com.sparta.NeflixCloneCodingProjectBack.dto.TheMovieApiResponseDto;
import com.sparta.NeflixCloneCodingProjectBack.dto.TheMovieApiResponseResultList;
import com.sparta.NeflixCloneCodingProjectBack.dto.VideoDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VideoController {
    private final VideoService videoService;
    private final VideoRepository videoRepository;


    public VideoController(VideoService videoService, VideoRepository videoRepository) {
        this.videoService = videoService;
        this.videoRepository = videoRepository;
    }

    @GetMapping("/api/main/db")
    public TheMovieApiResponseDto getDB() throws Exception {
        MovieSearchApi movieSearchApi = new MovieSearchApi();
        TheMovieApiResponseDto theMovieApiResponseDto = movieSearchApi.TheMovieDBSearchByGenre(MovieGenreId.Mystery.getValue());
        List<TheMovieApiResponseResultList> theMovieApiResponseResultList = theMovieApiResponseDto.getResults();

        for (int i = 0; i < theMovieApiResponseResultList.size(); i++) {
            TheMovieApiResponseResultList list = theMovieApiResponseResultList.get(i);
            VideoDto videoDto = new VideoDto(list);
            Video video = new Video(videoDto);
            videoRepository.save(video);

        }

        return theMovieApiResponseDto;
    }
}
