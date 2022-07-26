package me.reimarrosas.strelloapi.domain.user.mapper;

import me.reimarrosas.strelloapi.domain.user.dto.UserSignupDto;
import me.reimarrosas.strelloapi.domain.user.entity.UserEntity;
import me.reimarrosas.strelloapi.utils.IMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserSignupMapper extends IMapper<UserSignupDto, UserEntity> {
    UserSignupMapper INSTANCE = Mappers.getMapper(UserSignupMapper.class);
}
