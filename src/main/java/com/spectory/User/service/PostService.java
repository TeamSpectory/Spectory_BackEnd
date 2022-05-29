package com.spectory.User.service;

import com.spectory.User.domain.Post;
import com.spectory.User.domain.PostRepository;
import com.spectory.User.domain.User;
import com.spectory.User.domain.UserRepository;
import com.spectory.User.dto.PostListResponseDto;
import com.spectory.User.dto.PostSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long save(PostSaveRequestDto requestDto) {
        Optional<User> writer = userRepository.findById(requestDto.getUserIdx());
        return postRepository.save(requestDto.toEntity(writer.get())).getPostId();
    }

    @Transactional
    public List<PostListResponseDto> findAll(Long userIdx){

        Optional<User> writer = userRepository.findById(userIdx);
        List<Post> allPost = postRepository.findByWriter(writer.get());

        List<PostListResponseDto> rtnList = new ArrayList<PostListResponseDto>();
        for (Post p : allPost){
            rtnList.add(new PostListResponseDto(p.getType(),p.getTitle(),p.getStartDate(),p.getEndDate()
            ,p.getPicture(),p.getRates(),p.getTags()));
        }

        return rtnList;
    }

}
