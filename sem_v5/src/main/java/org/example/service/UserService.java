package org.example.service;

import org.example.dto.response.AuthResponse;
import org.example.dto.request.SignInRequest;
import org.example.dto.request.SignUpRequest;

public interface UserService {

    AuthResponse signUp(SignUpRequest request);

    AuthResponse signIn(SignInRequest request);

    void changeAvatar(Long id, String uuid);

    String getAvatar(Long id);
}
