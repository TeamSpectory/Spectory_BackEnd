package com.spectory.User.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserLoginRequestDto {
    private String id;
    private String pw;
    private String nickname;
}
