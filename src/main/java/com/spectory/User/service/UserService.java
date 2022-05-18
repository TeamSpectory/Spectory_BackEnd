package com.spectory.User.service;

import com.spectory.Message;
import com.spectory.User.domain.User;
import com.spectory.User.domain.UserRepository;
import com.spectory.User.dto.UserJoinRequestDto;
import com.spectory.User.dto.UserJoinResponseDto;
import com.spectory.User.dto.UserLoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    @Transactional
    public UserLoginResponseDto login(String id, String pw) throws Exception {
        Optional<User> user = userRepository.findById(id);

        if (!passwordEncoder.matches(pw, user.get().getPw())){
            throw new Exception(Message.ID_PW_ERROR);
        }
        else{
            return new UserLoginResponseDto(user.get().getUserId());
        }

    }
}
