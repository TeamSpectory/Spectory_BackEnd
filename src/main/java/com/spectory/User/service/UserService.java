package com.spectory.User.service;

import com.spectory.Config.JsonWebTokenProvider;
import com.spectory.Message;
import com.spectory.User.domain.User;
import com.spectory.User.domain.UserRepository;
import com.spectory.User.dto.*;
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
    private final JsonWebTokenProvider jsonWebTokenProvider;

    @Transactional
    public UserJoinResponseDto join(UserJoinRequestDto requestDto) throws Exception {
        if (requestDto.getId().length() == 0 || requestDto.getPw().length() == 0 || requestDto.getNickname().length() == 0) {
            throw new Exception(Message.MISSING_ARGUMENT);
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
        else {
            String token = jsonWebTokenProvider.createToken(id);
            return new UserLoginResponseDto(user.get().getUserId(), token);
        }
    }

    public UserProfileResponseDto getProfile(long userIdx) throws Exception {
        try {
            Optional<User> user = userRepository.findById(userIdx);
            return new UserProfileResponseDto(user.get().getNickname(), user.get().getId());
        } catch (Exception e) {
            throw new Exception(Message.INVALID_USER);
        }
    }

    @Transactional
    public boolean deleteUser(UserDeleteRequestDto userDeleteRequestDto, long userIdx) throws Exception {
        if (userDeleteRequestDto.getToken().isEmpty()) {
            throw new Exception(Message.MISSING_ARGUMENT); // 토큰 누락 시 바로 에러 처리.
        }

        if (jsonWebTokenProvider.validateToken(userDeleteRequestDto.getToken()) == false) {
            throw new Exception(Message.TOKEN_AUTHENTICATION_FAIL); // 토큰 인증 실패 시
        } else {
            try {
                userRepository.deleteById(userIdx);
                return true;
            } catch (Exception e) {
                throw new Exception(Message.DELETE_USER_FAIL); // 없는 유저거나 서버 내부 에러 등으로 실패한 경우.
            }
        }
    }
}
