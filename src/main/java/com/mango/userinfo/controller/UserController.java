package com.mango.userinfo.controller;

import com.mango.userinfo.dto.UserDto;
import com.mango.userinfo.entity.User;
import com.mango.userinfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> allUsers = userService.getAllUsers();
        return new ResponseEntity<> (allUsers, HttpStatus.OK);
    }

    @GetMapping("/findUserById/{userId}")
    public ResponseEntity<UserDto> findUserById(@PathVariable Integer userId) {
        return userService.findUserById(userId);
    }

    @PostMapping("/addUser")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        UserDto savedUserDto = userService.addUser(userDto);
        return new ResponseEntity<>(savedUserDto, HttpStatus.CREATED);
    }
}
