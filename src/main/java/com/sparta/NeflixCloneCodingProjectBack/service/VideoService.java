package com.sparta.NeflixCloneCodingProjectBack.service;

import com.sparta.NeflixCloneCodingProjectBack.dto.videoResponseDto.LargeCategoryDto;
import org.springframework.stereotype.Service;

@Service
public interface VideoService {
    public LargeCategoryDto getTvShow();

    public LargeCategoryDto findMovieToSmallCategory(String movie, String smallcategory);

    public LargeCategoryDto getMovie();

    LargeCategoryDto getRandomShow();
}
