package com.spectory.User.controller;

import com.spectory.Message;
import com.spectory.Status;
import com.spectory.User.dto.UserJoinRequestDto;
import com.spectory.User.dto.ResponseDto;
import com.spectory.User.dto.UserJoinResponseDto;
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
            return ResponseEntity.badRequest().body(ResponseDto.res(Status.BAD_REQUEST, Message.MISSING_ARGUMENT));
        }
    }
}
