package com.spectory.User.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UserLoginResponseDto {
        private Long userIdx;
        public UserLoginResponseDto (Long userIdx) {
            this.userIdx = userIdx;
        }
}
