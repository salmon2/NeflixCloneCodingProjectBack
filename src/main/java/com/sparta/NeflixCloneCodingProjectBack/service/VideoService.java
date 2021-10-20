package com.sparta.NeflixCloneCodingProjectBack.service;

import com.sparta.NeflixCloneCodingProjectBack.dto.videoResponseDto.VideoResponseDto;

import java.util.List;

public interface VideoService{

    public List<VideoResponseDto> findAll();
}
