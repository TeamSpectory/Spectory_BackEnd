package com.spectory.User.dto;

import com.spectory.User.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDeleteRequestDto {
    private String token;

    @Builder
    public UserDeleteRequestDto(String token) {
        this.token = token;
    }
}
