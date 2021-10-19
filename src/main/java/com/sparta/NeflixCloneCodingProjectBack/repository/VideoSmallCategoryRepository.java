package com.sparta.NeflixCloneCodingProjectBack.repository;

import com.sparta.NeflixCloneCodingProjectBack.domain.VideoSmallCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoSmallCategoryRepository
        extends JpaRepository<VideoSmallCategory, Long> {

}
