package com.sparta.NeflixCloneCodingProjectBack.controller;

import com.sparta.NeflixCloneCodingProjectBack.dto.videoResponseDto.VideoResponseDto;
import com.sparta.NeflixCloneCodingProjectBack.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class VideoController {

    public final VideoService videoService;

    @GetMapping("/video")
    public String findAllVideo(Model model) {
        List<VideoResponseDto> videoResponseDto = videoService.findAll();
        model.addAttribute("videoList", videoResponseDto);

        return "videoList";
    }

}
