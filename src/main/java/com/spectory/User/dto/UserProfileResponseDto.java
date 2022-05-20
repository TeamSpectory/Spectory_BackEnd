package com.spectory.User.dto;

import lombok.Data;

@Data
public class UserProfileResponseDto {
    private String nickname;
    private String id;

    public UserProfileResponseDto(String nickname, String id) {
        this.nickname = nickname;
        this.id = id;
    }
}
