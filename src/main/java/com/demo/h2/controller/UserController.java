package com.demo.h2.controller;

import com.demo.h2.entity.User;
import com.demo.h2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping()
    public User saveUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Integer userId) {
        userRepository.deleteById(userId);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") Integer userId, @RequestBody User user) {
        user.setId(userId);
        return userRepository.saveAndFlush(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Integer userId) {
        Optional<User> optional = userRepository.findById(userId);
        return optional.orElseGet(User::new);
    }

    @GetMapping("/list")
    public Page<User> pageQuery(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return userRepository.findAll(PageRequest.of(pageNum - 1, pageSize));
    }
}
