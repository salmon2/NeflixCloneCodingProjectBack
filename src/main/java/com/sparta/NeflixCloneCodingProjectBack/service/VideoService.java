package com.sparta.NeflixCloneCodingProjectBack.service;

import com.sparta.NeflixCloneCodingProjectBack.repository.VideoRepository;
import org.springframework.stereotype.Service;


@Service
public class VideoService {
    private final VideoRepository videoRepository;


    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

}
