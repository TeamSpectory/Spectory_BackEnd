package com.spectory.User.controller;

import com.spectory.Message;
import com.spectory.Status;
import com.spectory.User.dto.*;
import com.spectory.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserApiController {
    private final UserService userService;
    @PostMapping("join")
    public ResponseEntity join(@RequestBody UserJoinRequestDto requestDto) {
        try {
            UserJoinResponseDto userJoinResponseDto = userService.join(requestDto);
            return ResponseEntity.ok().body(ResponseDto.res(Status.OK, Message.JOIN_SUCCESS, userJoinResponseDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDto.res(Status.BAD_REQUEST, e.getMessage()));
        }
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody UserLoginRequestDto requestDto){
        try {
            UserLoginResponseDto userLoginResponseDto = userService.login(requestDto.getId(), requestDto.getPw());
            return ResponseEntity.ok().body(ResponseDto.res(Status.OK, Message.LOGIN_SUCCESS,userLoginResponseDto));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(ResponseDto.res(Status.BAD_REQUEST, Message.ID_PW_ERROR));
        }

    }

    @GetMapping("profile/{userIdx}")
    public ResponseEntity profile(@PathVariable("userIdx") long userIdx) {
        try {
            UserProfileResponseDto userProfileResponseDto = userService.getProfile(userIdx);
            return ResponseEntity.ok().body(ResponseDto.res(Status.OK, Message.GET_PROFILE_SUCCESS, userProfileResponseDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDto.res(Status.NOT_FOUND, Message.INVALID_USER));
        }
    }

    @DeleteMapping("delete/{userIdx}")
    public ResponseEntity deleteUser(@PathVariable("userIdx") long userIdx, @RequestBody UserDeleteRequestDto deleteRequestDto) {
        try {
            userService.deleteUser(deleteRequestDto, userIdx);
            return ResponseEntity.ok().body(ResponseDto.res(Status.OK, Message.DELETE_USER_SUCCESS));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDto.res(Status.BAD_REQUEST, e.getMessage()));
        }
    }

}
