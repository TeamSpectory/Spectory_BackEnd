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

import java.util.NoSuchElementException;
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
            Optional<User> user = userRepository.findById(requestDto.getId());
            if (user.isEmpty() == false) {
                throw new Exception(Message.ALREADY_EXIST);
            }
            String password = passwordEncoder.encode(requestDto.getPw());
            UserJoinRequestDto dto = new UserJoinRequestDto(requestDto.getId(), password, requestDto.getNickname());
            Long userIdx = userRepository.save(dto.toEntity()).getUserId();
            return new UserJoinResponseDto(userIdx);
        }
    }

    @Transactional
    public UserLoginResponseDto login(String id, String pw) throws Exception {
        try{
            Optional<User> user = userRepository.findById(id);
            if (!passwordEncoder.matches(pw, user.get().getPw())){
                throw new Exception(Message.ID_PW_ERROR);
            }
            else {
                String token = jsonWebTokenProvider.createToken(id);
                return new UserLoginResponseDto(user.get().getUserId(), token);
            }
        }catch (NoSuchElementException e){
            throw new NoSuchElementException(Message.ID_PW_ERROR);
        }

    }

    public UserProfileResponseDto getProfile(long userIdx) throws Exception {
        try {
            Optional<User> user = userRepository.findById(userIdx);
            return new UserProfileResponseDto(user.get().getNickname(), user.get().getId(), user.get().getCreated_date());
        } catch (Exception e) {
            throw new Exception(Message.INVALID_USER);
        }
    }

    @Transactional
    public boolean deleteUser(UserDeleteRequestDto userDeleteRequestDto, long userIdx) throws Exception {
        Optional<User> user = userRepository.findById(userIdx);
        if (user.isEmpty()) {
            throw new Exception(Message.INVALID_USER);
        }

        if (userDeleteRequestDto.getToken().isEmpty()) {
            throw new Exception(Message.MISSING_ARGUMENT);
        } else {
            if (jsonWebTokenProvider.validateToken(userDeleteRequestDto.getToken()) == false) {
                throw new Exception(Message.TOKEN_AUTHENTICATION_FAIL);
            }
        }

        try {
            userRepository.deleteById(userIdx);
            return true;
        } catch (Exception e) {
            throw new Exception(Message.INTERNAL_SERVER_ERR);
        }
    }
}
