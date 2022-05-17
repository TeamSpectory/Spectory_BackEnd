package com.spectory.User.service;

import com.spectory.User.domain.UserRepository;
import com.spectory.User.dto.UserJoinRequestDto;
import com.spectory.User.dto.UserJoinResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserJoinResponseDto join(UserJoinRequestDto requestDto) throws Exception {
        if (requestDto.getId().length() == 0 || requestDto.getPw().length() == 0 || requestDto.getNickname().length() == 0) {
            throw new Exception("필요 데이터 누락");
        } else {
            String password = passwordEncoder.encode(requestDto.getPw());
            UserJoinRequestDto dto = new UserJoinRequestDto(requestDto.getId(), password, requestDto.getNickname());
            Long userIdx = userRepository.save(dto.toEntity()).getUserId();
            return new UserJoinResponseDto(userIdx);
        }
    }
}
