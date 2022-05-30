package com.spectory.User.domain;
import com.spectory.Config.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(length=30, nullable = false, unique = true)
    private String id;

    @Column(length=60, nullable = false)
    private String pw;

    @Column(length=30, nullable = false)
    private String nickname;

    @Builder
    public User(String id, String pw, String nickname) {
        this.id = id;
        this.pw = pw;
        this.nickname = nickname;
    }
}
