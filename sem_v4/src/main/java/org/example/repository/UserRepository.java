package org.example.repository;

import org.example.model.UserEntity;

import java.util.Optional;

public interface UserRepository {

    Optional<UserEntity> findUserById(Long id);

    Optional<UserEntity> findUserByEmail(String email);

    Optional<UserEntity> findUserByNickname(String nickname);

    Optional<UserEntity> saveNewUser(UserEntity user);

    void updateAvatar(Long id, String uuid);

    String getAvatar(Long id);
}
