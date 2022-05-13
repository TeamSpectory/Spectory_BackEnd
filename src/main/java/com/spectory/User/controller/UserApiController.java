package com.spectory.User.controller;

import com.spectory.Message;
import com.spectory.Status;
import com.spectory.User.dto.UserJoinRequestDto;
import com.spectory.User.dto.ResponseDto;
import com.spectory.User.dto.UserJoinResponseDto;
import com.spectory.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        UserJoinResponseDto userJoinResponseDto = userService.join(requestDto);
        return new ResponseEntity(ResponseDto.res(Status.OK, Message.JOIN_SUCCESS, userJoinResponseDto), HttpStatus.OK);
    }
}
