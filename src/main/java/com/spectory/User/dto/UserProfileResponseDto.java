package com.spectory.User.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserProfileResponseDto {
    private String nickname;
    private String id;
    private LocalDateTime created_date;

    public UserProfileResponseDto(String nickname, String id, LocalDateTime createdDate) {
        this.nickname = nickname;
        this.id = id;
        this.created_date = createdDate;
    }
}
