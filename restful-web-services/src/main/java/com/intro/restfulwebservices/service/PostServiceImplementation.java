package com.intro.restfulwebservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intro.restfulwebservices.model.Post;
import com.intro.restfulwebservices.repository.PostRepository;

import java.util.List;

@Component
public class PostServiceImplementation implements PostService {
    private PostRepository postRepository;

    @Autowired
    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public List<Post> getPostsByUserId(Long id) {
        return postRepository.findAllByUserId(id);
    }
}
