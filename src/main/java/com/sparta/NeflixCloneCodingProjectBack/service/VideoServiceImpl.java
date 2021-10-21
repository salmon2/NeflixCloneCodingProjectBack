package com.sparta.NeflixCloneCodingProjectBack.service;

import com.sparta.NeflixCloneCodingProjectBack.MovieApi.AllGenre;
import com.sparta.NeflixCloneCodingProjectBack.MovieApi.DramaGenre;
import com.sparta.NeflixCloneCodingProjectBack.MovieApi.MovieGenre;
import com.sparta.NeflixCloneCodingProjectBack.domain.SmallCategory;
import com.sparta.NeflixCloneCodingProjectBack.domain.Video;
import com.sparta.NeflixCloneCodingProjectBack.domain.VideoSmallCategory;
import com.sparta.NeflixCloneCodingProjectBack.dto.videoResponseDto.LargeCategoryDto;
import com.sparta.NeflixCloneCodingProjectBack.dto.videoResponseDto.SmallCategoryDto;
import com.sparta.NeflixCloneCodingProjectBack.dto.videoResponseDto.VideoResponseDto;
import com.sparta.NeflixCloneCodingProjectBack.repository.VideoRepository;
import com.sparta.NeflixCloneCodingProjectBack.repository.SmallCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService{
    private final VideoRepository videoRepository;
    private final SmallCategoryRepository smallCategoryRepository;

    private final String tvShow = "tvShow";
    private final String movie = "movie";
    private final String random = "random";



    @Override
    public LargeCategoryDto getTvShow() {
        LargeCategoryDto largeCategoryDto = null;

        List<SmallCategoryDto> smallCategoryDtoList = new ArrayList<>();
        for (DramaGenre genre : DramaGenre.values()) {
            List<String> duplicateCheck = new ArrayList<>();

            List<VideoResponseDto> videoResponseDtoList = new ArrayList<>();
            findVideoListByCategory(tvShow, genre.getGenreName(), videoResponseDtoList, duplicateCheck);

            SmallCategoryDto smallCategoryDto = new SmallCategoryDto(videoResponseDtoList.size(), genre.getGenreName(), videoResponseDtoList);
            smallCategoryDtoList.add(smallCategoryDto);
        }

        largeCategoryDto = new LargeCategoryDto(tvShow, smallCategoryDtoList);

        return largeCategoryDto;
    }

    @Override
    public LargeCategoryDto getMovie() {
        LargeCategoryDto largeCategoryDto = null;

        List<SmallCategoryDto> smallCategoryDtoList = new ArrayList<>();
        for (MovieGenre genre : MovieGenre.values()) {
            List<String> duplicateCheck = new ArrayList<>();

            List<VideoResponseDto> videoResponseDtoList = new ArrayList<>();
            findVideoListByCategory(movie, genre.getGenreName(), videoResponseDtoList, duplicateCheck);

            SmallCategoryDto smallCategoryDto = new SmallCategoryDto(videoResponseDtoList.size(), genre.getGenreName(), videoResponseDtoList);
            smallCategoryDtoList.add(smallCategoryDto);
        }

        largeCategoryDto = new LargeCategoryDto(movie, smallCategoryDtoList);

        return largeCategoryDto;
    }

    @Override
    public LargeCategoryDto getRandomShow() {
        LargeCategoryDto largeCategoryDto = null;

        List<SmallCategoryDto> smallCategoryDtoList = new ArrayList<>();
        for (AllGenre genre : AllGenre.values()) {
            List<String> duplicateCheck = new ArrayList<>();

            List<VideoResponseDto> videoResponseDtoList = new ArrayList<>();
            findRandomVideoListByCategory(random, genre.getGenreName(), videoResponseDtoList, duplicateCheck);

            SmallCategoryDto smallCategoryDto = new SmallCategoryDto(videoResponseDtoList.size(), genre.getGenreName(), videoResponseDtoList);
            smallCategoryDtoList.add(smallCategoryDto);
        }

        largeCategoryDto = new LargeCategoryDto(random, smallCategoryDtoList);

        return largeCategoryDto;
    }

    @Override
    public LargeCategoryDto findMovieToSmallCategory(String movie, String smallcategory) {
        List<SmallCategoryDto> smallCategoryDtoList = new ArrayList<>();
        List<VideoResponseDto> videoResponseDtoList = new ArrayList<>();
        List<String> duplicateCheck = new ArrayList<>();

        findVideoListByCategory(movie, smallcategory, videoResponseDtoList, duplicateCheck);

        SmallCategoryDto smallCategoryDto = new SmallCategoryDto(videoResponseDtoList.size(), smallcategory, videoResponseDtoList);
        smallCategoryDtoList.add(smallCategoryDto);

        LargeCategoryDto largeCategoryDto = new LargeCategoryDto(movie, smallCategoryDtoList);

        return largeCategoryDto;
    }

    private void findRandomVideoListByCategory(String LargeCategory, String key, List<VideoResponseDto> videoResponseDtoList, List<String> duplicateCheck) {
        SmallCategory findSmallCategory = smallCategoryRepository.findBySmallCategoryName(key);
        System.out.println("Genre = " + key);

        List<VideoSmallCategory> videoSmallCategoryList = findSmallCategory.getVideoSmallCategoryList();

        int videoCount = 0;
        for (int i = 0; i < 20; i++) {
            int index;
            if( i % 2 == 0){
                index = videoSmallCategoryList.size() - i-1;
            }
            else
                index = i;

            VideoSmallCategory videoSmallCategory = videoSmallCategoryList.get(index);

            Video video = videoSmallCategory.getVideo();
            boolean contains = checkEffectiveness(duplicateCheck, video, LargeCategory);

            if(contains == false)
                break;
            else{
                duplicateCheck.add(video.getTitle());
                VideoResponseDto newVideoResponseDto = videoToVideoResponseDto(video);
                videoResponseDtoList.add(newVideoResponseDto);
                videoCount +=1;
            }

        }
    }



    private void findVideoListByCategory(String LargeCategory, String key, List<VideoResponseDto> videoResponseDtoList, List<String> duplicateCheck) {
        SmallCategory findSmallCategory = smallCategoryRepository.findBySmallCategoryName(key);
        System.out.println("Genre = " + key);

        List<VideoSmallCategory> videoSmallCategoryList = findSmallCategory.getVideoSmallCategoryList();

        int videoCount = 0;
        for (VideoSmallCategory videoSmallCategory : videoSmallCategoryList) {
            if(videoCount > 20) {
                return;
            }

            Video video = videoSmallCategory.getVideo();
            boolean contains = checkEffectiveness(duplicateCheck, video, LargeCategory);

            if(contains == false)
                continue;
            else{
                duplicateCheck.add(video.getTitle());
                VideoResponseDto newVideoResponseDto = videoToVideoResponseDto(video);
                videoResponseDtoList.add(newVideoResponseDto);
                videoCount +=1;
            }
        }
    }

    private boolean checkEffectiveness(List<String> duplicateCheck, Video video, String largeCategory) {
        boolean contains = duplicateCheck.contains(video.getTitle());
        String largeCategoryName = video.getLargeCategory().getLargeCategoryName();
        boolean nameEquals = largeCategory.equals(video.getLargeCategory().getLargeCategoryName());


        if(contains == true){
            return false;
        }
        else{
            if(nameEquals == true || largeCategory == "random")
                return true;
            else
                return false;
        }

    }

    private VideoResponseDto videoToVideoResponseDto(Video video) {
        List<VideoSmallCategory> videoSmallCategoryList1 = video.getVideoSmallCategoryList();
        List<String> genre = new ArrayList<>();
        for (VideoSmallCategory smallCategory : videoSmallCategoryList1) {
            genre.add(smallCategory.getSmallCategory().getSmallCategoryName());
        }

        VideoResponseDto newVideoResponseDto = new VideoResponseDto(genre, video.getId(), video.getTitle(), video.getPosterPath(), video.getOverview(),
                video.getRelease_date(), video.getVote_average(), video.getYoutubePath(), video.getBackdrop_path());
        return newVideoResponseDto;
    }
}
