package com.sparta.NeflixCloneCodingProjectBack.video;

import org.springframework.stereotype.Service;


@Service
public class VideoService {
    private final VideoRepository videoRepository;


    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

}
