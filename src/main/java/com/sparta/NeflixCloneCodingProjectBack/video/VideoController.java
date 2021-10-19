package com.sparta.NeflixCloneCodingProjectBack.video;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoController {
    private final VideoService videoService;


    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }


}
