package com.sparta.NeflixCloneCodingProjectBack.video;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoController {
    private final VideoService videoService;
    private final VideoRepository videoRepository;


    public VideoController(VideoService videoService, VideoRepository videoRepository) {
        this.videoService = videoService;
        this.videoRepository = videoRepository;
    }

}
