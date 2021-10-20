package com.sparta.NeflixCloneCodingProjectBack.scheduled;

import com.sparta.NeflixCloneCodingProjectBack.MovieApi.MovieGenre;
import com.sparta.NeflixCloneCodingProjectBack.MovieApi.MovieSearchApi;
import com.sparta.NeflixCloneCodingProjectBack.domain.LargeCategory;
import com.sparta.NeflixCloneCodingProjectBack.domain.SmallCategory;
import com.sparta.NeflixCloneCodingProjectBack.domain.Video;
import com.sparta.NeflixCloneCodingProjectBack.domain.VideoSmallCategory;
import com.sparta.NeflixCloneCodingProjectBack.dto.themovieapibygenredto.TheMovieApiByGenreResponseDto;
import com.sparta.NeflixCloneCodingProjectBack.dto.themovieapibygenredto.TheMovieApiResponseResultList;
import com.sparta.NeflixCloneCodingProjectBack.dto.themovieapibyiddto.TheMovieApiByIdResponseDto;
import com.sparta.NeflixCloneCodingProjectBack.dto.themovieapibyiddto.VideoListResult;
import com.sparta.NeflixCloneCodingProjectBack.repository.LargeCategoryRepository;
import com.sparta.NeflixCloneCodingProjectBack.repository.SmallCategoryRepository;
import com.sparta.NeflixCloneCodingProjectBack.repository.VideoSmallCategoryRepository;
import com.sparta.NeflixCloneCodingProjectBack.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class Schedule {
    private final MovieSearchApi movieSearchApi;
    private final VideoRepository videoRepository;
    private final SmallCategoryRepository smallCategoryRepository;
    private final LargeCategoryRepository largeCategoryRepository;
    private final VideoSmallCategoryRepository videoSmallCategoryRepository;

    private static int count = 0;

    @Scheduled(fixedDelay = 1000000) // scheduler 끝나는 시간 기준으로 1000 간격으로 실행
    @Transactional
    public void scheduleDelayTask() throws Exception {
        count = count +1;
        System.out.println(count + "번째 스케쥴링 시작");
        videoRepository.deleteAll();

        TheMovieApiByGenreResponseDto theMovieApiResponseDto = movieSearchApi.TheMovieDBSearchByGenre(MovieGenre.Action);
        List<TheMovieApiResponseResultList> results = theMovieApiResponseDto.getResults();

        for (TheMovieApiResponseResultList result : results) {

            Long id = result.getId();
            TheMovieApiByIdResponseDto theMovieApiByIdResponseDto = movieSearchApi.TheMovieDBSearchById(id);
            VideoListResult[] videoList = theMovieApiByIdResponseDto.getVideos().getResults();

            VideoListResult findVideoUrl;

            findVideoUrl = findVideoUrl(videoList);

            //장르 찾기
            List<MovieGenre> genreList = findGenreList(result);

            //===============================================================

            //large Category
            LargeCategory largeCategory = largeCategorySaveOrFind();

            //video
            Video newVideo = videoSaveOrFind(result, findVideoUrl, largeCategory);

            //smallCategory
            smallCategorySaveOrFind(genreList);

            vedioSmallCategorySaveOrFind(newVideo, genreList);

        }

    }

    private VideoListResult findVideoUrl(VideoListResult[] videoList) {
        for (VideoListResult videoListResult : videoList) {
            if(videoListResult.getType().equals("Trailer")){
                return videoListResult;
            }
        }
        return null;
    }

    private void vedioSmallCategorySaveOrFind(Video newVideo, List<MovieGenre> genreList) {
        for (MovieGenre moviegenre : genreList) {
            SmallCategory findSmallCategory = smallCategoryRepository.findBySmallCategoryName(moviegenre.getGenreName());
            VideoSmallCategory newVideoSmallCategory = new VideoSmallCategory(newVideo, findSmallCategory);
            videoSmallCategoryRepository.save(newVideoSmallCategory);
        }
    }

    private void smallCategorySaveOrFind(List<MovieGenre> genreList) {
        for (MovieGenre moviegenre : genreList) {
            SmallCategory findSmallCategory = smallCategoryRepository.findBySmallCategoryName(moviegenre.getGenreName());
            if (findSmallCategory == null){
                SmallCategory newSmallCategory = new SmallCategory(moviegenre.getGenreName(), moviegenre.getValue());
                smallCategoryRepository.save(newSmallCategory);
            }
        }
    }

    private Video videoSaveOrFind(TheMovieApiResponseResultList result, VideoListResult videoListResult, LargeCategory largeCategory) {
        Video newVideo = new Video(result, videoListResult, largeCategory);
        videoRepository.save(newVideo);
        return newVideo;
    }

    private LargeCategory largeCategorySaveOrFind() {
        LargeCategory largeCategory = largeCategoryRepository.findByLargeCategoryName("movie");

        if(largeCategory == null){
            LargeCategory newLargeCategory = new LargeCategory("movie");
            newLargeCategory = largeCategoryRepository.save(newLargeCategory);
            return newLargeCategory;
        }

        return largeCategory;
    }

    private List<MovieGenre> findGenreList(TheMovieApiResponseResultList result) {
        List<MovieGenre> selectGenreList = new ArrayList<>();

        for (Long genreId: result.getGenre_ids()) {
            for (MovieGenre value :MovieGenre.values()) {
                if (genreId.intValue() == value.getValue()){
                    selectGenreList.add(value);
                }
            }
        }
        return selectGenreList;
    }

}
