package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.response.AuthResponse;
import org.example.dto.request.SignInRequest;
import org.example.dto.request.SignUpRequest;
import org.example.dto.response.UserDataResponse;
import org.example.mapper.UserMapper;
import org.example.model.UserEntity;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.example.util.AuthUtils;

import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public AuthResponse signUp(SignUpRequest request) {
        if(request.getEmail() == null || request.getEmail().isBlank())
            return response(1, "Empty email", null);

        if(request.getPassword() == null || request.getPassword().isBlank())
            return response(2, "Empty password", null);

        if(request.getNickname() == null || request.getNickname().isBlank())
            return response(3, "Empty nickname", null);

        if(!AuthUtils.checkPassword(request.getPassword()))
            return response(4, "Invalid password", null);

        if(userRepository.findUserByEmail(request.getEmail()).isPresent())
            return response(6, "Email taken", null);

        if(userRepository.findUserByNickname(request.getNickname()).isPresent())
            return response(7, "Nickname taken", null);

        Optional<UserEntity> optionalUser = userRepository.saveNewUser(userMapper.toEntity(request));

        if(optionalUser.isEmpty())
            return response(50, "Database process error", null);

        return response(0, "OK", userMapper.toDto(optionalUser.get()));

    }

    @Override
    public AuthResponse signIn(SignInRequest request) {
        if(request.getEmail() == null || request.getEmail().isBlank())
            return response(1, "Empty email", null);

        if(request.getPassword() == null || request.getPassword().isBlank())
            return response(2, "Empty password", null);

        if(!AuthUtils.checkEmail(request.getEmail()))
            return response(4, "Invalid email", null);

        Optional<UserEntity> optionalUser = userRepository.findUserByEmail(request.getEmail());

        if(optionalUser.isEmpty())
            return response(8, "Email not found", null);

        UserEntity user = optionalUser.get();

        if(!AuthUtils.verifyPassword(request.getPassword(), user.getHashPassword()))
            return response(9, "Password mismatch", null);

        return response(0, "OK", userMapper.toDto(user));
    }

    @Override
    public void changeAvatar(Long id, String uuid) {
        userRepository.updateAvatar(id, uuid);
    }

    @Override
    public String getAvatar(Long id) {
        return userRepository.getAvatar(id);
    }

    private AuthResponse response(int status, String statusDesc, UserDataResponse user) {
        return AuthResponse.builder()
                .status(status)
                .statusDesc(statusDesc)
                .user(user)
                .build();
    }
}
