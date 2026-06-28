package com.textile.erp.user.mapper;

import com.textile.erp.user.dto.UserDto;
import com.textile.erp.user.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserDto toDto(User user);
}
