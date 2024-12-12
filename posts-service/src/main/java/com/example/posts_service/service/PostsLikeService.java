package com.example.posts_service.service;

import com.example.posts_service.dto.PostDto;
import com.example.posts_service.entity.PostLike;
import com.example.posts_service.repository.LikesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostsLikeService {
    private final ModelMapper modelMapper;
    private final LikesRepository likesRepository;
    public PostDto likePost(Long userId, Long postId){
        PostLike postLike = PostLike.builder()
                .postId(postId)
                .userId(userId)
                .build();

        return modelMapper.map(likesRepository.save(postLike), PostDto.class);
    }

    @Transactional
    public PostDto unlikePost(long l, Long postId) {
        PostLike postLike = likesRepository.findByPostId(postId).orElseThrow(() -> new RuntimeException("post not found !"));
        likesRepository.deleteByPostId(postId);
        return modelMapper.map(postLike, PostDto.class);
    }
}
