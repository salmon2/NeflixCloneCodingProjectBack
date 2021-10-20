package com.sparta.NeflixCloneCodingProjectBack.repository;

import com.sparta.NeflixCloneCodingProjectBack.domain.SmallCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmallCategoryRepository extends JpaRepository<SmallCategory, Long> {
    public SmallCategory findBySmallCategoryName(String SmallCategory);
    public List<SmallCategory> findAllBySmallCategoryName(String SmallCategory);


}
