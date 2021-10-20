package com.sparta.NeflixCloneCodingProjectBack.service;

import com.sparta.NeflixCloneCodingProjectBack.MovieApi.MovieGenre;
import com.sparta.NeflixCloneCodingProjectBack.domain.SmallCategory;
import com.sparta.NeflixCloneCodingProjectBack.domain.Video;
import com.sparta.NeflixCloneCodingProjectBack.domain.VideoSmallCategory;
import com.sparta.NeflixCloneCodingProjectBack.dto.themovieapibygenredto.TheMovieApiResponseResultList;
import com.sparta.NeflixCloneCodingProjectBack.dto.videoResponseDto.VideoResponseDto;
import com.sparta.NeflixCloneCodingProjectBack.repository.VideoRepository;
import com.sparta.NeflixCloneCodingProjectBack.repository.VideoSmallCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;
    private final VideoSmallCategoryRepository videoSmallCategoryRepository;

    @Override
    public List<VideoResponseDto> findAll() {
        List<Video> responseVideo = videoRepository.findAll();
        List<VideoResponseDto> videoResponseDtos = new ArrayList<>();


        for (Video video : responseVideo) {

            Long id = video.getId();
            List<String> genreList = findGenreList(id);

            VideoResponseDto videoResponseDto =
                    new VideoResponseDto(genreList, video.getId(), video.getTitle(),
                            video.getPosterPath(), video.getOverview(),
                            video.getRelease_date(), video.getVote_average(),
                            video.getYoutubePath());
            videoResponseDtos.add(videoResponseDto);
        }
        return videoResponseDtos;
    }

    private List<String> findGenreList(Long id) {
        List<String> selectGenreList = new ArrayList<>();

        List<VideoSmallCategory> vsc = videoSmallCategoryRepository.findAllByVideoId(id);
        for (VideoSmallCategory vs : vsc) {
            for (MovieGenre value :MovieGenre.values()) {
                if (vs.getSmallCategory().getSmallCategoryName().equals(value.getGenreName())){
                    String genreName = value.getGenreName();
                    selectGenreList.add(genreName);
                }
            }
        }
        return selectGenreList;
    }
}
