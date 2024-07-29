package com.example.backend.controller;

import java.util.UUID;

import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping(value = "/users")
public class UsersController {

    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<UUID> addUser(@RequestBody User user) {
        if (user != null) {
            var id  = UUID.randomUUID();
            user.setId(id);
            userRepository.save(user);
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable UUID id) {
        return ResponseEntity.ok(userRepository.findById(id).orElse(null));
    }

    @PutMapping("/{id}")
    ResponseEntity<User> modifyUser(@PathVariable UUID id, @RequestBody User user) {

        if (user != null && id != null && userRepository.existsById(id)) {
            user.setId(id);
            userRepository.save(user);
        } else {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<User> deleteUser(@PathVariable UUID id) {

        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/login")
    public ResponseEntity<User> login(@RequestHeader String emailOrUsername, @RequestHeader String password) {

        final var user = userRepository.findByEmail(emailOrUsername)
                                       .orElse(userRepository.findByUsername(emailOrUsername)
                                                             .orElse(null));
        if (user != null) {
            if (user.getPassword().equals(password)) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
