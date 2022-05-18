package com.spectory;

import com.spectory.User.domain.User;
import com.spectory.User.domain.UserRepository;
import com.spectory.User.dto.UserJoinRequestDto;
import com.spectory.User.dto.UserLoginResponseDto;
import com.spectory.User.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @Test
    public void 로그인() throws Exception {

        //given
        String id = "aaa";
        String pw = "1234";
        String nickname = "temp";


        //when
        userService.join(new UserJoinRequestDto(id, pw, nickname));
        userService.login(id, pw);

        //then
        //예외 발생 시 테스트 실패

    }
}
