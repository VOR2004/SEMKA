package org.example.mapper.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.request.SignUpRequest;
import org.example.dto.response.UserDataResponse;
import org.example.mapper.UserMapper;
import org.example.model.UserEntity;
import org.example.util.AuthUtils;

@Slf4j
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity toEntity(SignUpRequest request) {
        return UserEntity.builder()
                .email(request.getEmail())
                .nickname(request.getNickname())
                .hashPassword(AuthUtils.hashPassword(request.getPassword()))
                .build();
    }

    @Override
    public UserDataResponse toDto(UserEntity entity) {
        return UserDataResponse.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .nickname(entity.getNickname())
                .build();
    }
}
