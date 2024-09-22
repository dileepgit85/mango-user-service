package com.mango.userinfo.service;

import com.mango.userinfo.dto.UserDto;
import com.mango.userinfo.entity.User;
import com.mango.userinfo.mapper.UserMapper;
import com.mango.userinfo.repo.UserRepo;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    public List<UserDto> getAllUsers() {
        List<User> users =  userRepo.findAll();
        return users.stream().map(user -> UserMapper.INSTANCE.mapUserToUserDto(user)).collect(Collectors.toList());
    }

    public UserDto addUser(UserDto userDto) {
        User savedUser =  userRepo.save(UserMapper.INSTANCE.mapUserDtoToUser(userDto));
        return UserMapper.INSTANCE.mapUserToUserDto(savedUser);
    }

    public ResponseEntity<UserDto> findUserById(Integer userId) {
        Optional<User> optionalUser = userRepo.findById(userId);
        if(optionalUser.isPresent()) {
            return new ResponseEntity<>(UserMapper.INSTANCE.mapUserToUserDto(optionalUser.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
