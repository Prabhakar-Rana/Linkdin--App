package com.example.posts_service.controller;

import com.example.posts_service.dto.PostDto;
import com.example.posts_service.service.PostsLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class LikesController {
    private final PostsLikeService postsLikeService;
    @PostMapping("likes/{postId}")
    ResponseEntity<PostDto> likePost(@PathVariable Long postId){
        return new ResponseEntity<>(postsLikeService.likePost(1L, postId), HttpStatus.CREATED);
    }

    @DeleteMapping("unlike/{postId}")
    ResponseEntity<PostDto> unLikePost(@PathVariable Long postId){
        System.out.println("i am in unlike post");
        return new ResponseEntity<>(postsLikeService.unlikePost(1L, postId), HttpStatus.CREATED);
    }

}
