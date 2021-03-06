package com.spectory.Post.domain;

import com.spectory.User.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByWriter(User user);
    Optional<Post> findById(Long postId);
}
