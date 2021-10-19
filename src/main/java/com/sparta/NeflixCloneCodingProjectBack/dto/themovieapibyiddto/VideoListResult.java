package com.sparta.NeflixCloneCodingProjectBack.dto.themovieapibyiddto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoListResult {
    private String name ;
    private String key;
    private String site;
    private Long size;
    private String type;
    private Boolean official;
    private String published_at;
    private String id;
}
