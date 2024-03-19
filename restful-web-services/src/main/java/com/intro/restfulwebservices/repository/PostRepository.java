package com.intro.restfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intro.restfulwebservices.model.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUserId(Long user_id);
}
