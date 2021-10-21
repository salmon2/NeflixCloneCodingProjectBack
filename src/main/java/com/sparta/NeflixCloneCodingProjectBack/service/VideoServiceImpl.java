package com.sparta.NeflixCloneCodingProjectBack.service;

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


    @Override
    public LargeCategoryDto process(String LargeGenre) {

        LargeCategoryDto largeCategoryDto = null; 
        if(LargeGenre.equals("movie")) {

            List<SmallCategoryDto> smallCategoryDtoList = new ArrayList<>();
            for (MovieGenre genre : MovieGenre.values()) {
                List<String> duplicateCheck = new ArrayList<>();

                List<VideoResponseDto> videoResponseDtoList = new ArrayList<>();
                findVideoListByCategory(genre.getGenreName(), videoResponseDtoList, duplicateCheck);

                SmallCategoryDto smallCategoryDto = new SmallCategoryDto(videoResponseDtoList.size(), genre.getGenreName(), videoResponseDtoList);
                smallCategoryDtoList.add(smallCategoryDto);
            }

            largeCategoryDto = new LargeCategoryDto(LargeGenre, smallCategoryDtoList);
            
        } else if (LargeGenre.equals("drama")) {
            
            List<SmallCategoryDto> smallCategoryDtoList = new ArrayList<>();
            for (DramaGenre genre : DramaGenre.values()) {
                List<String> duplicateCheck = new ArrayList<>();

                List<VideoResponseDto> videoResponseDtoList = new ArrayList<>();
                findVideoListByCategory(genre.getGenreName(), videoResponseDtoList, duplicateCheck);

                SmallCategoryDto smallCategoryDto = new SmallCategoryDto(videoResponseDtoList.size(), genre.getGenreName(), videoResponseDtoList);
                smallCategoryDtoList.add(smallCategoryDto);
            }

            largeCategoryDto = new LargeCategoryDto(LargeGenre, smallCategoryDtoList);
                       
        }
        return largeCategoryDto;
    }

    @Override
    public LargeCategoryDto findtosmallcategory(String movie, String smallcategory) {
        List<SmallCategoryDto> smallCategoryDtoList = new ArrayList<>();
        List<VideoResponseDto> videoResponseDtoList = new ArrayList<>();
        List<String> duplicateCheck = new ArrayList<>();

        findVideoListByCategory(smallcategory, videoResponseDtoList, duplicateCheck);

        SmallCategoryDto smallCategoryDto = new SmallCategoryDto(videoResponseDtoList.size(), smallcategory, videoResponseDtoList);
        smallCategoryDtoList.add(smallCategoryDto);

        LargeCategoryDto largeCategoryDto = new LargeCategoryDto(movie, smallCategoryDtoList);

        return largeCategoryDto;
    }

    private void findVideoListByCategory(String key, List<VideoResponseDto> videoResponseDtoList, List<String> duplicateCheck) {
        SmallCategory findSmallCategory = smallCategoryRepository.findBySmallCategoryName(key);
        System.out.println("Genre = " + key);

        List<VideoSmallCategory> videoSmallCategoryList = findSmallCategory.getVideoSmallCategoryList();



        int videoCount = 0;
        for (VideoSmallCategory videoSmallCategory : videoSmallCategoryList) {
            if(videoCount > 20) {
                return;
            }

            Video video = videoSmallCategory.getVideo();
            boolean contains = duplicateCheck.contains(video.getTitle());

            if(contains == true)
                break;
            else{
                duplicateCheck.add(video.getTitle());
                VideoResponseDto newVideoResponseDto = vedioToVideoResponseDto(video);
                videoResponseDtoList.add(newVideoResponseDto);
                videoCount +=1;
            }
        }
    }

    private VideoResponseDto vedioToVideoResponseDto(Video video) {
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
