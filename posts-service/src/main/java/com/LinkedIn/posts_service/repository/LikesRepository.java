package com.LinkedIn.posts_service.repository;

import com.LinkedIn.posts_service.entity.Post;
import com.LinkedIn.posts_service.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<PostLike, Long> {
    Optional<PostLike> findByPostId(Long postId);

    void deleteByPostId(Long postId);
}
