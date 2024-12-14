package org.example.mapper;

import org.example.dto.request.SignUpRequest;
import org.example.dto.response.UserDataResponse;
import org.example.model.UserEntity;
public interface UserMapper {

    UserEntity toEntity(SignUpRequest request);

    UserDataResponse toDto(UserEntity entity);

}
