package com.spectory;

import com.spectory.User.domain.UserRepository;
import com.spectory.User.dto.UserJoinRequestDto;
import com.spectory.User.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @Test
    public void 로그인_성공() throws Exception {

        //given
        String id = "abc";
        String pw = "123";
        String nickname = "temp";

        //when
        userService.join(new UserJoinRequestDto(id, pw, nickname));

        //then
        //예외 발생 시 테스트 실패
        userService.login(id, pw);

    }

    @Test
    public void 로그인_실패() throws Exception {

        //given
        String id = "abc";
        String pw = "123";
        String nickname = "temp";

        //when
        userService.join(new UserJoinRequestDto(id, pw, nickname));

        //then

        //no id
        try {
            userService.login("abcd", pw);
        } catch (NoSuchElementException e) {
            assertThat(e.getMessage()).isEqualTo(Message.ID_PW_ERROR);
        }

        //pw no match
        try {
            userService.login(id, "1234");
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo(Message.ID_PW_ERROR);
        }

    }
}
