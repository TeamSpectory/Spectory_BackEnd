package com.spectory.Post.dto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostDeleteRequestDto {
    private String token;

    @Builder
    public PostDeleteRequestDto(String token) {
        this.token = token;
    }
}
