package com.spectory.Post.controller;

import com.spectory.Message;
import com.spectory.Post.domain.Post;
import com.spectory.Post.dto.*;
import com.spectory.Status;
import com.spectory.User.dto.ResponseDto;
import com.spectory.Post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/post", consumes = MediaType.APPLICATION_JSON_VALUE)
public class PostApiController {
    private final PostService postService;

    @PostMapping(value = "write", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity save(PostSaveRequestDto postSaveRequestDto, @RequestPart(required = false) MultipartFile image){
        try{
            postService.save(postSaveRequestDto, image);
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
    public ResponseEntity findAll(@PathVariable("userIdx") Long userIdx) {
        List<PostListResponseDto> postListResponseDtoList;
        try {
            postListResponseDtoList = postService.findAll(userIdx);
            return ResponseEntity.ok().body(ResponseDto.res(Status.OK, Message.GET_ALL_POST_SUCCESS, postListResponseDtoList));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDto.res(Status.BAD_REQUEST, e.getMessage()));
        }
    }

    @GetMapping("detail/{postIdx}")
    public ResponseEntity getDetailPost(@PathVariable("postIdx") Long postIdx) {
        try {
            Optional<Post> post = postService.getDetail(postIdx);
            PostDetailResponseDto postDetailResponseDto = new PostDetailResponseDto(post);
            return ResponseEntity.ok().body(ResponseDto.res(Status.OK, Message.GET_DETAIL_POST_SUCCESS, postDetailResponseDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDto.res(Status.BAD_REQUEST, e.getMessage()));
        }
    }

    @DeleteMapping("delete/{postIdx}")
    public ResponseEntity deletePost(@PathVariable("postIdx") Long postIdx, @RequestBody PostDeleteRequestDto postDeleteRequestDto) {
        try {
            postService.deletePost(postDeleteRequestDto, postIdx);
            return ResponseEntity.ok(ResponseDto.res(Status.OK, Message.DELETE_POST_SUCCESS));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDto.res(Status.BAD_REQUEST, e.getMessage()));
        }
    }

    @DeleteMapping("delete-all/{userIdx}")
    public ResponseEntity deleteAllPost(@PathVariable("userIdx") Long userIdx, @RequestBody PostDeleteRequestDto postDeleteRequestDto) {
        try {
            postService.deleteAllPost(postDeleteRequestDto, userIdx);
            return ResponseEntity.ok(ResponseDto.res(Status.OK, Message.DELETE_POST_SUCCESS));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDto.res(Status.BAD_REQUEST, e.getMessage()));
        }

    }
}
