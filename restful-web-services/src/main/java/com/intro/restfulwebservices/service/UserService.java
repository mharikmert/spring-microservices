package com.intro.restfulwebservices.service;

import com.intro.restfulwebservices.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getUsers();
    User getUserById(Long id);
    User createUser(User user);
    void deleteUser(Long id);
}
