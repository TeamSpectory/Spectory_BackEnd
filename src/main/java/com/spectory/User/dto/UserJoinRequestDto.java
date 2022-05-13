package com.spectory.User.dto;

import com.spectory.User.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserJoinRequestDto {
    private String id;
    private String pw;
    private String nickname;

    @Builder
    public UserJoinRequestDto (String id, String pw, String nickname) {
        this.id = id;
        this.pw = pw;
        this.nickname = nickname;
    }

    public User toEntity() {
        return User.builder()
                .id(id)
                .pw(pw)
                .nickname(nickname)
                .build();
    }
}
