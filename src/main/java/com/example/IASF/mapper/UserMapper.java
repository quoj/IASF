package com.example.IASF.mapper;

import com.example.IASF.dto.req.UserReq;
import com.example.IASF.dto.res.UserRes;
import com.example.IASF.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserReq userReq);
    UserRes toDTO(User user);
}
