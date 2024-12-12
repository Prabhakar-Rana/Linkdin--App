package com.example.posts_service.controller;



import com.example.posts_service.clients.ConnectionsClient;
import com.example.posts_service.dto.PersonDto;
import com.example.posts_service.dto.PostCreateDto;
import com.example.posts_service.dto.PostDto;
import com.example.posts_service.service.PostsService;
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
    private final ConnectionsClient connectionsClient;
    @PostMapping
    ResponseEntity<PostDto> createPost(@RequestBody PostCreateDto postCreateDto,
                                       HttpServletRequest httpServletRequest ){
        return new ResponseEntity<>(postsService.createPost(postCreateDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    ResponseEntity<PostDto> getPostById(@PathVariable Long id, HttpServletRequest httpServletRequest){
        String userId = httpServletRequest.getHeader("X-Authenticated-User");
        List<PersonDto> first_degree = connectionsClient.getFirstConnections();
        return ResponseEntity.ok(postsService.getPostById(id));
    }

    @GetMapping("/users/{userId}/allPosts")
    ResponseEntity<List<PostDto>> getAllPost(@PathVariable Long userId){
        System.out.println("valu");
        System.out.println("value="+userId);
        return ResponseEntity.ok(postsService.getAllPost(userId));
    }

}
