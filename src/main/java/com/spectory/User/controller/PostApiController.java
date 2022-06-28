package com.spectory.User.controller;

import com.spectory.Message;
import com.spectory.Status;
import com.spectory.User.domain.Post;
import com.spectory.User.dto.PostListResponseDto;
import com.spectory.User.dto.PostModifyRequestDto;
import com.spectory.User.dto.PostSaveRequestDto;
import com.spectory.User.dto.ResponseDto;
import com.spectory.User.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/post", consumes = MediaType.APPLICATION_JSON_VALUE)
public class PostApiController {

    private final PostService postService;

    @PostMapping("write")
    public ResponseEntity save(@RequestBody PostSaveRequestDto postSaveRequestDto){

        try{
            postService.save(postSaveRequestDto);
            return ResponseEntity.ok().body(ResponseDto.res(Status.OK, Message.POST_SAVE_SUCCESS));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(ResponseDto.res(Status.BAD_REQUEST, e.getMessage()));
        }

    }

    @PatchMapping("modify/{postIdx}")
    public ResponseEntity modify(@PathVariable("postIdx") Long postIdx, @RequestBody PostModifyRequestDto postModifyRequestDto) {

        try {
            postService.modify(postIdx, postModifyRequestDto);
            return ResponseEntity.ok().body(ResponseDto.res(Status.OK, Message.POST_MODIFY_SUCCESS));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDto.res(Status.BAD_REQUEST, e.getMessage()));
        }

    }

    @GetMapping("list/{userIdx}")
    public List<PostListResponseDto> findAll(@PathVariable("userIdx") Long userIdx){
        List<PostListResponseDto> postListResponseDtoList = postService.findAll(userIdx);
        return postListResponseDtoList;
    }


}
