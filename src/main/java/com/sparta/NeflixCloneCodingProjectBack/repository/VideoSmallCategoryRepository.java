package com.sparta.NeflixCloneCodingProjectBack.repository;

import com.sparta.NeflixCloneCodingProjectBack.domain.VideoSmallCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoSmallCategoryRepository
        extends JpaRepository<VideoSmallCategory, Long> {
    List<VideoSmallCategory> findAllByVideoId(Long videoid);
}
