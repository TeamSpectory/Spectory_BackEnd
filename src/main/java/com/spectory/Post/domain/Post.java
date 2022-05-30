package com.spectory.Post.domain;

import com.spectory.User.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column
    private Long type;

    @ManyToOne
    @JoinColumn(name="writer",referencedColumnName = "userId", nullable = false)
    private User writer;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(length = 50)
    private String situation;

    @Column(length = 300, columnDefinition = "TEXT")
    private String action;

    @Column(length = 300, columnDefinition = "TEXT")
    private String learned;

    @Column(length = 300)
    private String picture;

    @Column
    private int rates;

    @Column(length = 50)
    private String tags;

    @Builder
    public Post(Long type, User writer, String title, LocalDate startDate, LocalDate endDate, String situation, String action, String learned, String picture, int rates, String tags) {
        this.type = type;
        this.writer = writer;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.situation = situation;
        this.action = action;
        this.learned = learned;
        this.picture = picture;
        this.rates = rates;
        this.tags = tags;
    }

}
