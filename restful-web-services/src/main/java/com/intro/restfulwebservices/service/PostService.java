package com.intro.restfulwebservices.service;

import org.springframework.stereotype.Service;

import com.intro.restfulwebservices.model.Post;

import java.util.List;

@Service
public interface PostService {
    Post createPost(Post post);
    List<Post> getPostsByUserId(Long id);
}