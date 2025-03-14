package org.senla.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.senla.dto.RegisterDto;
import org.senla.dto.RegisterResponse;
import org.senla.entity.User;

@Mapper
public interface UserMapper {
    @Mapping(source = "username", target = "name")
    User toUser(RegisterDto registerDto);
    RegisterResponse toRegisterResponse(User user);
}
