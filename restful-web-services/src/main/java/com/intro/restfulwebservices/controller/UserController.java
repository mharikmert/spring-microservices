package com.intro.restfulwebservices.controller;

import com.intro.restfulwebservices.exception.UserNotFoundException;
import com.intro.restfulwebservices.model.Post;
import com.intro.restfulwebservices.model.PostType;
import com.intro.restfulwebservices.model.User;
import com.intro.restfulwebservices.service.PostService;
import com.intro.restfulwebservices.service.UserService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());
    private final UserService userService;
    private final PostService postService;

    public UserController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = Optional.ofNullable(userService.getUserById(id));
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        return ResponseEntity.ok(user.get());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User createdUser = userService.createUser(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();

        logger.info("User created: " + createdUser);

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<User> user = Optional.ofNullable(userService.getUserById(id));
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        userService.deleteUser(id);
        logger.info("User deleted: " + user.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{id}/posts")
    public ResponseEntity<List<Post>> getUserPosts(@PathVariable Long id) {
        Optional<User> user = Optional.ofNullable(userService.getUserById(id));
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        return ResponseEntity.ok(postService.getPostsByUserId(id));
    }

    @PostMapping(path = "/{id}/posts")
    public ResponseEntity<Post> createUserPost(@PathVariable Long id, @RequestBody Post post) {

        Optional<User> user = Optional.ofNullable(userService.getUserById(id));
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found with id: " + id);
        }

        post.setUser(user.get());

        Post createdPost = postService.createPost(post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdPost.getId())
                .toUri();

        logger.info("Post created: " + createdPost);

        return ResponseEntity.created(location).build();
    }
}
