//package com.spectory.domain.User;
//
//
//import com.spectory.User.domain.User;
//import com.spectory.User.domain.UserRepository;
//import org.aspectj.lang.annotation.After;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.util.List;
//
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//public class UserRepositoryTest {
//    @Autowired
//    UserRepository userRepository;
//
//    @Test
//    public void userJoinTest() {
//        //given
//        String id = "aaa";
//        String pw = "1234";
//        String nickname = "temp";
//
//        userRepository.save(User.builder()
//                .id(id)
//                .pw(pw)
//                .nickname(nickname)
//                .build());
//
//        //when
//        List<User> userList = userRepository.findAll();
//
//        //then
//        User user = userList.get(0);
//        assertThat(user.getId()).isEqualTo(id);
//        assertThat(user.getNickname()).isEqualTo(nickname);
//        assertThat(user.getPw()).isEqualTo(pw);
//    }
//}
