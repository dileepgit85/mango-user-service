package com.mango.userinfo.service;

import com.mango.userinfo.dto.UserDto;
import com.mango.userinfo.entity.User;
import com.mango.userinfo.mapper.UserMapper;
import com.mango.userinfo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserRepo userRepo;
    public List<UserDto> getAllUsers() {
        List<User> users =  userRepo.findAll();
        return users.stream().map(UserMapper.INSTANCE::mapUserToUserDto).collect(Collectors.toList());
    }

    public UserDto addUser(UserDto userDto) {
        User savedUser =  userRepo.save(UserMapper.INSTANCE.mapUserDtoToUser(userDto));
        return UserMapper.INSTANCE.mapUserToUserDto(savedUser);
    }

    public ResponseEntity<UserDto> findUserById(Integer userId) {
        Optional<User> optionalUser = userRepo.findById(userId);
        return optionalUser.map(user -> new ResponseEntity<>(UserMapper.INSTANCE.mapUserToUserDto(user), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    public ResponseEntity exportExcel(String userId) {
        return restTemplate.getForObject("http://ORDER-SERVICE/excel/generateExcel?order=" + userId, ResponseEntity.class);
    }

    public String exportExcelTest(String order) {
        return restTemplate.getForObject("http://ORDER-SERVICE/excel/test?order=" + order, String.class);
    }
}
