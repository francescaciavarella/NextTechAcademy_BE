package com.infobasic.ciavarella.nexttechacademy.controller;

import com.infobasic.ciavarella.nexttechacademy.model.User;
import com.infobasic.ciavarella.nexttechacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable String role) {
        List<User> users = userService.getUsersByRole(role);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/students")
    public ResponseEntity<List<User>> getAllStudentUsers() {
        List<User> users = userService.getAllStudentUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/teachers")
    public ResponseEntity<List<User>> getAllTeacherUsers() {
        List<User> users = userService.getAllTeacherUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/admins")
    public ResponseEntity<List<User>> getAllAdminUsers() {
        List<User> users = userService.getAllAdminUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

