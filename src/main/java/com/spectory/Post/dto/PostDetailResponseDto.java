package com.spectory.Post.dto;

import com.spectory.Post.domain.Post;
import lombok.Getter;
import java.time.LocalDate;
import java.util.Optional;

@Getter
public class PostDetailResponseDto {
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private String situation;
    private String action;
    private String learned;
    private String picture;
    private int rates;
    private String tags;

    public PostDetailResponseDto(Optional<Post> post) {
        this.title = post.get().getTitle();
        this.startDate = post.get().getStartDate();
        this.endDate = post.get().getEndDate();
        this.situation = post.get().getSituation();
        this.action = post.get().getAction();
        this.learned = post.get().getLearned();
        this.picture = post.get().getPicture();
        this.rates = post.get().getRates();
        this.tags = post.get().getTags();
    }
}
