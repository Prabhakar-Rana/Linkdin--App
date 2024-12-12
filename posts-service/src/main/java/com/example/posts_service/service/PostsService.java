package com.example.posts_service.service;



import com.example.posts_service.auth.UserContextHolder;
import com.example.posts_service.dto.PostCreateDto;
import com.example.posts_service.dto.PostDto;
import com.example.posts_service.entity.Post;
import com.example.posts_service.event.PostCreatedEvent;
import com.example.posts_service.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostsService {
    private final ModelMapper modelMapper;
    private final PostsRepository postsRepository;
    private final KafkaTemplate<String, Object>kafkaTemplate;
    public PostDto createPost(PostCreateDto postCreateDto){
        Post post = modelMapper.map(postCreateDto, Post.class);
        Long id = UserContextHolder.getUserId();
        post.setUserId(id);
        PostCreatedEvent postLikedEvent = PostCreatedEvent.builder()
                .creatorId(id)
                .postId(post.getId())
                .content(post.getContent())
                .build();
        kafkaTemplate.send("post-created-topic", postLikedEvent);
        return modelMapper.map(postsRepository.save(post), PostDto.class);
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
