package com.sparta.NeflixCloneCodingProjectBack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto<T> {
    private Long statusCode;
    private String msg;
    private T data;
}
