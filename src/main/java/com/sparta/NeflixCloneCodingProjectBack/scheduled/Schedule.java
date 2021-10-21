package com.sparta.NeflixCloneCodingProjectBack.scheduled;

import com.sparta.NeflixCloneCodingProjectBack.MovieApi.DramaGenre;
import com.sparta.NeflixCloneCodingProjectBack.MovieApi.MovieGenre;
import com.sparta.NeflixCloneCodingProjectBack.MovieApi.MovieSearchApi;
import com.sparta.NeflixCloneCodingProjectBack.domain.LargeCategory;
import com.sparta.NeflixCloneCodingProjectBack.domain.SmallCategory;
import com.sparta.NeflixCloneCodingProjectBack.domain.Video;
import com.sparta.NeflixCloneCodingProjectBack.domain.VideoSmallCategory;
import com.sparta.NeflixCloneCodingProjectBack.dto.themovieapibygenredto.TheDramaApiGenreResponseDto;
import com.sparta.NeflixCloneCodingProjectBack.dto.themovieapibygenredto.TheDramaApiResponseResultList;
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
    private final static int time = 1000 * 60 * 60 * 24;


    @Scheduled(fixedDelay = time) // scheduler 끝나는 시간 기준으로 1000 간격으로 실행
    @Transactional
    public void scheduleDelayTask() throws Exception {
        count = count +1;
        System.out.println(count + "번째 스케쥴링 시작");
        videoRepository.deleteAll();

        for (MovieGenre genre : MovieGenre.values()) {
            saveAllDomainDataByGenre(genre);
        }
        for (DramaGenre genre : DramaGenre.values()) {
            saveAllDomainDataByDramaGenre(genre);
        }


    }

    private void saveAllDomainDataByGenre(MovieGenre genre) throws Exception {
        TheMovieApiByGenreResponseDto theMovieApiResponseDto = movieSearchApi.TheMovieDBSearchByGenre(genre);
        List<TheMovieApiResponseResultList> results = theMovieApiResponseDto.getResults();

        List<String> duplicateCheck = new ArrayList<>();

                int videoCount = 0;
        for (TheMovieApiResponseResultList result : results) {
            System.out.println("videoCount = " + videoCount);
            if(videoCount > 10)
                break;

            Long id = result.getId();
            TheMovieApiByIdResponseDto theMovieApiByIdResponseDto = movieSearchApi.TheMovieDBSearchById(id);
            VideoListResult[] videoList = theMovieApiByIdResponseDto.getVideos().getResults();

            VideoListResult findVideoUrl;

            findVideoUrl = findVideoUrl(videoList);

            //장르 찾기
            List<MovieGenre> genreList = findGenreList(result);

            //===============================================================

            //large Category
            LargeCategory largeCategory = largeCategorySaveOrFind("movie");

            //video
            Video newVideo = videoSaveOrFind(result, findVideoUrl, largeCategory, duplicateCheck);

            //smallCategory
            smallCategorySaveOrFind(genreList);

            videoSmallCategorySaveOrFind(newVideo, genreList);
            videoCount +=1;
        }
    }

    private void saveAllDomainDataByDramaGenre(DramaGenre genre) throws Exception {
        TheDramaApiGenreResponseDto theMovieApiResponseDto = movieSearchApi.TheDramaDBSearchByGenre(genre);
        List<TheDramaApiResponseResultList> results = theMovieApiResponseDto.getResults();

        int videoCount = 0;
        for (TheDramaApiResponseResultList result : results) {
            System.out.println("videoCount = " + videoCount);
            if(videoCount > 10)
                break;

            Long id = result.getId();
            TheMovieApiByIdResponseDto theMovieApiByIdResponseDto = movieSearchApi.TheDramaDBSearchById(id);
            VideoListResult[] videoList = theMovieApiByIdResponseDto.getVideos().getResults();

            VideoListResult findVideoUrl;

            findVideoUrl = findVideoUrl(videoList);

            //장르 찾기
            List<DramaGenre> genreList = findGenreDramaList(result);

            //===============================================================

            //large Category
            LargeCategory largeCategory = largeCategorySaveOrFind("tvShow");

            //video
            Video newVideo = DramaSaveOrFind(result, findVideoUrl, largeCategory);

            //smallCategory
            DramasmallCategorySaveOrFind(genreList);

            DramaSmallCategorySaveOrFind(newVideo, genreList);
            videoCount +=1;
        }
    }

    //movie
    private VideoListResult findVideoUrl(VideoListResult[] videoList) {
        for (VideoListResult videoListResult : videoList) {
            if(videoListResult.getType().equals("Trailer")){
                return videoListResult;
            }
        }
        return null;
    }

    private void videoSmallCategorySaveOrFind(Video newVideo, List<MovieGenre> genreList) {
        if (newVideo != null){
            for (MovieGenre moviegenre : genreList) {
                SmallCategory findSmallCategory = smallCategoryRepository.findBySmallCategoryName(moviegenre.getGenreName());
                VideoSmallCategory newVideoSmallCategory = new VideoSmallCategory(newVideo, findSmallCategory);
                videoSmallCategoryRepository.save(newVideoSmallCategory);
            }
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

    private Video videoSaveOrFind(TheMovieApiResponseResultList result, VideoListResult videoListResult, LargeCategory largeCategory, List<String> duplicateCheck) {
        boolean contains = duplicateCheck.contains(result.getTitle());
        if (contains == true)
            return null;

        duplicateCheck.add(result.getTitle());
        Video newVideo = new Video(result, videoListResult, largeCategory);
        videoRepository.save(newVideo);
        return newVideo;
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


    //drama
    private void DramasmallCategorySaveOrFind(List<DramaGenre> genreList) {
        for (DramaGenre dramaGenre : genreList) {
            SmallCategory findSmallCategory = smallCategoryRepository.findBySmallCategoryName(dramaGenre.getGenreName());
            if (findSmallCategory == null){
                SmallCategory newSmallCategory = new SmallCategory(dramaGenre.getGenreName(), dramaGenre.getValue());
                smallCategoryRepository.save(newSmallCategory);
            }
        }
    }

    private Video DramaSaveOrFind(TheDramaApiResponseResultList result, VideoListResult videoListResult, LargeCategory largeCategory) {
        Video newVideo = new Video(result, videoListResult, largeCategory);
        videoRepository.save(newVideo);
        return newVideo;
    }

    private List<DramaGenre> findGenreDramaList(TheDramaApiResponseResultList result) {
        List<DramaGenre> selectGenreList = new ArrayList<>();

        for (Long genreId: result.getGenre_ids()) {
            for (DramaGenre value :DramaGenre.values()) {
                if (genreId.intValue() == value.getValue()){
                    selectGenreList.add(value);
                }
            }
        }
        return selectGenreList;
    }

    private void DramaSmallCategorySaveOrFind(Video newVideo, List<DramaGenre> genreList) {
        for (DramaGenre dramaGenre : genreList) {
            SmallCategory findSmallCategory = smallCategoryRepository.findBySmallCategoryName(dramaGenre.getGenreName());
            VideoSmallCategory newVideoSmallCategory = new VideoSmallCategory(newVideo, findSmallCategory);
            videoSmallCategoryRepository.save(newVideoSmallCategory);
        }
    }


    //largecategory 저장
    private LargeCategory largeCategorySaveOrFind(String largecategory) {
        LargeCategory largeCategory = largeCategoryRepository.findByLargeCategoryName(largecategory);

        if(largeCategory == null){
            LargeCategory newLargeCategory = new LargeCategory(largecategory);
            newLargeCategory = largeCategoryRepository.save(newLargeCategory);
            return newLargeCategory;
        }

        return largeCategory;
    }


}
