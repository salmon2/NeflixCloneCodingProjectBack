package com.sparta.NeflixCloneCodingProjectBack.service;

import com.sparta.NeflixCloneCodingProjectBack.dto.LargeCategoryDto;
import com.sparta.NeflixCloneCodingProjectBack.repository.VideoRepository;
import org.springframework.stereotype.Service;


@Service
public interface VideoService {
    public LargeCategoryDto process(String genre);

}
