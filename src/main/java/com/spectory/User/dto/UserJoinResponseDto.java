package com.spectory.User.dto;

import lombok.Data;

@Data
public class UserJoinResponseDto {
    private Long userIdx;

    public UserJoinResponseDto (Long userIdx) {
        this.userIdx = userIdx;
    }
}
