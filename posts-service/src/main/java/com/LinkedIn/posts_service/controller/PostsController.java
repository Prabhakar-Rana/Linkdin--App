package com.LinkedIn.posts_service.controller;

import com.LinkedIn.posts_service.dto.PostCreateDto;
import com.LinkedIn.posts_service.dto.PostDto;
import com.LinkedIn.posts_service.service.PostsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/core")
@RequiredArgsConstructor
public class PostsController {
    private final PostsService postsService;
    @PostMapping
    ResponseEntity<PostDto> createPost(@RequestBody PostCreateDto postCreateDto,
                                       HttpServletRequest httpServletRequest ){
        return new ResponseEntity<>(postsService.createPost(postCreateDto, 1L), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    ResponseEntity<PostDto> getPostById(@PathVariable Long id, HttpServletRequest httpServletRequest){
        String userId = httpServletRequest.getHeader("X-Authenticated-User");
        System.out.println("userid in postController="+userId);
        return ResponseEntity.ok(postsService.getPostById(id));
    }

    @GetMapping("/users/{userId}/allPosts")
    ResponseEntity<List<PostDto>> getAllPost(@PathVariable Long userId){
        System.out.println("valu");
        System.out.println("value="+userId);
        return ResponseEntity.ok(postsService.getAllPost(userId));
    }

}
