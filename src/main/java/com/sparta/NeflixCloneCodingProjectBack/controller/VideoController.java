package com.sparta.NeflixCloneCodingProjectBack.controller;

import com.sparta.NeflixCloneCodingProjectBack.dto.videoResponseDto.LargeCategoryDto;
import com.sparta.NeflixCloneCodingProjectBack.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VideoController {

    public final VideoService videoService;

    @GetMapping("/video")
    public List<LargeCategoryDto> findAllVideo(Model model, @RequestParam String largeCategory) {
        List<LargeCategoryDto> videoResponseDto = videoService.findAll(largeCategory);
        model.addAttribute("videoList", videoResponseDto);

        return videoResponseDto;
    }

}
