package com.sparta.NeflixCloneCodingProjectBack.dto.videoResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoResponseDto {
    private List<String> genre;
    private Long id;
    private String title;
    private String poster_path;
    private String overview;
    private String first_date;
    private Float grade;
    private String youtubePath;
}
