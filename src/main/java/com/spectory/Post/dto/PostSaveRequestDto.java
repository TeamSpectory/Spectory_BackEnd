package com.spectory.Post.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spectory.Post.domain.Post;
import com.spectory.User.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class PostSaveRequestDto {

    private Long type;
    private String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private String situation;
    private String action;
    private String learned;
    private String picture;
    private int rates;
    private String tags;
    private Long userIdx;

    @Builder
    public PostSaveRequestDto(Long type, String title, LocalDate startDate, LocalDate endDate, String situation, String action, String learned, String picture, int rates, String tags, Long userIdx) {
        this.type = type;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.situation = situation;
        this.action = action;
        this.learned = learned;
        this.picture = picture;
        this.rates = rates;
        this.tags = tags;
        this.userIdx = userIdx;
    }

    public Post toEntity(User writer){
        return Post.builder()
                .type(type)
                .writer(writer)
                .title(title)
                .startDate(startDate)
                .endDate(endDate)
                .situation(situation)
                .action(action)
                .learned(learned)
                .picture(picture)
                .rates(rates)
                .tags(tags)
                .build();
    }

}
