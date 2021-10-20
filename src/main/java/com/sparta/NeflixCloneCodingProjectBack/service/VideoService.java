package com.sparta.NeflixCloneCodingProjectBack.service;

import com.sparta.NeflixCloneCodingProjectBack.dto.videoResponseDto.LargeCategoryDto;

import java.util.List;

public interface VideoService{

    public List<LargeCategoryDto> findAll(String movie);
}
