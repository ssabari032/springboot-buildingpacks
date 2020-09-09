package com.stacksimplify.restservices.mapper;

import com.stacksimplify.restservices.dtos.UserMmDto;
import com.stacksimplify.restservices.dtos.UserMsDto;
import com.stacksimplify.restservices.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface UserMapper {
    UserMapper INSTANCE= Mappers.getMapper(UserMapper.class);

    //User to UserMsDTO's
    UserMsDto UserToUserMsDto(User user);

    //List of Users to List of UsersMsDTO's
    List<UserMsDto> UsersToUserDto(List<User> users);
}
