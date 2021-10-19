package com.sparta.NeflixCloneCodingProjectBack.video;

import com.sparta.NeflixCloneCodingProjectBack.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
