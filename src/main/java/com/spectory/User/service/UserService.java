package com.spectory.User.service;

import com.spectory.User.domain.UserRepository;
import com.spectory.User.dto.UserJoinRequestDto;
import com.spectory.User.dto.UserJoinResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserJoinResponseDto join(UserJoinRequestDto requestDto) {
        Long userIdx = userRepository.save(requestDto.toEntity()).getUserId();
        return new UserJoinResponseDto(userIdx);
    }
}
