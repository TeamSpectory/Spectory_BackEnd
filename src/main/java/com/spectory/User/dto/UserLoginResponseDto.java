package com.spectory.User.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UserLoginResponseDto {
        private Long userIdx;
        private String token;

        public UserLoginResponseDto (Long userIdx, String token) {
            this.userIdx = userIdx;
            this.token = token;
        }
}
