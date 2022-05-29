package com.spectory.User.dto;

import com.spectory.User.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PostListResponseDto {

    private Long type;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private String picture;
    private int rates;
    private String tags;

    public PostListResponseDto(Long type, String title, LocalDate startDate, LocalDate endDate, String picture, int rates, String tags) {
        this.type = type;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.picture = picture;
        this.rates = rates;
        this.tags = tags;
    }

}
