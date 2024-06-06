package ru.barkhatnat.utils;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.barkhatnat.DTO.UserResponseDto;
import ru.barkhatnat.entity.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserResponseDto toUserResponse(User user);
}