package com.LinkedIn.posts_service.service;

import com.LinkedIn.posts_service.dto.PostCreateDto;
import com.LinkedIn.posts_service.dto.PostDto;
import com.LinkedIn.posts_service.entity.Post;
import com.LinkedIn.posts_service.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostsService {
    private final ModelMapper modelMapper;
    private final PostsRepository postsRepository;
    public PostDto createPost(PostCreateDto postCreateDto, Long id){
        Post post = modelMapper.map(postCreateDto, Post.class);
        post.setUserId(id);
        return modelMapper.map(postsRepository.save(post),PostDto.class);
    }

    public PostDto getPostById(Long id){
        Post post = postsRepository.findById(id).orElse(null);
        return modelMapper.map(post, PostDto.class);
    }

    public List<PostDto> getAllPost(long userId) {
        System.out.println("val="+userId);
        List<Post>posts = postsRepository.findByUserId(userId);
        return posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }
}
