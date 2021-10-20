package com.sparta.NeflixCloneCodingProjectBack.controller;

import com.sparta.NeflixCloneCodingProjectBack.dto.LargeCategoryDto;
import com.sparta.NeflixCloneCodingProjectBack.dto.ResponseDto;
import com.sparta.NeflixCloneCodingProjectBack.service.VideoService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;

    @GetMapping("/video")
    public ResponseDto videoLargeCategory(@RequestParam String largeCategory){
        LargeCategoryDto result = videoService.process(largeCategory);

        return new ResponseDto(200L, "성공", result);
    }

}
