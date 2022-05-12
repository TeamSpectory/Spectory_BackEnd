package com.spectory.User.domain;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(length=30, nullable = false)
    private String id;

    @Column(length=30, nullable = false)
    private String pw;

    @Column(length=30, nullable = false)
    private String nickname;
}
