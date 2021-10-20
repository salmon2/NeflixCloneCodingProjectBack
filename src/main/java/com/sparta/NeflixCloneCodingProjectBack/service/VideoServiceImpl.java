package com.sparta.NeflixCloneCodingProjectBack.service;

import com.sparta.NeflixCloneCodingProjectBack.MovieApi.MovieGenre;
import com.sparta.NeflixCloneCodingProjectBack.domain.LargeCategory;
import com.sparta.NeflixCloneCodingProjectBack.domain.SmallCategory;
import com.sparta.NeflixCloneCodingProjectBack.domain.Video;
import com.sparta.NeflixCloneCodingProjectBack.domain.VideoSmallCategory;
import com.sparta.NeflixCloneCodingProjectBack.dto.LargeCategoryDto;
import com.sparta.NeflixCloneCodingProjectBack.dto.SmallCategoryDto;
import com.sparta.NeflixCloneCodingProjectBack.dto.VideoResponseDto;
import com.sparta.NeflixCloneCodingProjectBack.repository.SmallCategoryRepository;
import com.sparta.NeflixCloneCodingProjectBack.repository.VideoRepository;
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

        List<SmallCategoryDto> smallCategoryDtoList = new ArrayList<>();
        for (MovieGenre genre : MovieGenre.values()) {
            List<VideoResponseDto> videoResponseDtoList = new ArrayList<>();
            findVideoListByCategory(genre.getGenreName(), videoResponseDtoList);

            SmallCategoryDto smallCategoryDto = new SmallCategoryDto(videoResponseDtoList.size(), genre.getGenreName(), videoResponseDtoList);
            smallCategoryDtoList.add(smallCategoryDto);
        }

        LargeCategoryDto largeCategoryDto = new LargeCategoryDto(LargeGenre, smallCategoryDtoList);

        return largeCategoryDto;
    }

    private void findVideoListByCategory(String key, List<VideoResponseDto> videoResponseDtoList) {
        SmallCategory findSmallCategory = smallCategoryRepository.findBySmallCategoryName(key);
        System.out.println("Genre = " + key);

        List<VideoSmallCategory> videoSmallCategoryList = findSmallCategory.getVideoSmallCategoryList();


        for (VideoSmallCategory videoSmallCategory : videoSmallCategoryList) {
            Video video = videoSmallCategory.getVideo();

            List<VideoSmallCategory> videoSmallCategoryList1 = video.getVideoSmallCategoryList();
            List<String> genre = new ArrayList<>();
            for (VideoSmallCategory smallCategory : videoSmallCategoryList1) {
                genre.add(smallCategory.getSmallCategory().getSmallCategoryName());
            }

            VideoResponseDto newVideoResponseDto = new VideoResponseDto(genre, video.getId(), video.getTitle(), video.getPosterPath(), video.getOverview(),
                    video.getRelease_date(), video.getVote_average(), video.getYoutubePath(), video.getBackdrop_path());

            videoResponseDtoList.add(newVideoResponseDto);
        }





//        LargeCategoryDto result = new LargeCategoryDto("movie",);



    }
}
