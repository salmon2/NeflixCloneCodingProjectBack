package com.sparta.NeflixCloneCodingProjectBack.dto.videoResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SmallCategoryDto {
    private String smallCategory;
    private List<VideoResponseDto> dataList = new ArrayList<>();
}
