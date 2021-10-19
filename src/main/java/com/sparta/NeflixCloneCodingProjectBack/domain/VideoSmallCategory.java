package com.sparta.NeflixCloneCodingProjectBack.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class VideoSmallCategory {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "videoId")
    private Video video;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "smallCategoryId")
    private SmallCategory smallCategory;

    public VideoSmallCategory(Video video, SmallCategory smallCategory) {
        this.video = video;
        this.smallCategory = smallCategory;
    }
}
