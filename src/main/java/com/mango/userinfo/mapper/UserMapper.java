package com.mango.userinfo.mapper;

import com.mango.userinfo.dto.UserDto;
import com.mango.userinfo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDto mapUserToUserDto(User user);
    User mapUserDtoToUser(UserDto userDto);
}
