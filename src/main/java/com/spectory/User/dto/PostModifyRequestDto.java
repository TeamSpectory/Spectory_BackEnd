package com.spectory.User.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PostModifyRequestDto {

    private String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private String situation;
    private String action;
    private String learned;
    private String picture;
    private int rates;
    private String tags;

    public PostModifyRequestDto(String title, LocalDate startDate, LocalDate endDate, String situation, String action, String learned, String picture, int rates, String tags) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.situation = situation;
        this.action = action;
        this.learned = learned;
        this.picture = picture;
        this.rates = rates;
        this.tags = tags;
    }

}
