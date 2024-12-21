package org.example.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.UserEntity;
import org.example.repository.UserRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final String SQL_SELECT_BY_ID = "select * from user_data where id = ?";

    private static final String SQL_SELECT_AVATAR_BY_ID = "select avatar from user_data where id = ?";

    private static final String SQL_UPDATE_AVATAR = "update user_data set avatar = ? where id = ?";

    private static final String SQL_SELECT_BY_EMAIL = "select * from user_data where email = ?";

    private static final String SQL_SELECT_BY_NICKNAME = "select * from user_data where nickname = ?";

    private static final String SQL_INSERT = "insert into user_data(email, hash_password, nickname) values (?, ?, ?)";

    private final UserRowMapper userRowMapper = new UserRowMapper();
    @Override
    public Optional<UserEntity> findUserById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, userRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserEntity> findUserByEmail(String email) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, userRowMapper, email));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserEntity> findUserByNickname(String nickname) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_NICKNAME, new Object[] {nickname}, userRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserEntity> saveNewUser(UserEntity user) {
        try {
            KeyHolder holder = new GeneratedKeyHolder();
            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(SQL_INSERT, new String[] {"id"});
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getHashPassword());
                ps.setString(3, user.getNickname());
                return ps;
            }, holder);
            Long id = Objects.requireNonNull(holder.getKey()).longValue();
            return findUserById(id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void updateAvatar(Long id, String uuid) {
        jdbcTemplate.update(SQL_UPDATE_AVATAR, uuid, id);
    }

    @Override
    public String getAvatar(Long id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_AVATAR_BY_ID, String.class, id);
    }

    private static final class UserRowMapper implements RowMapper<UserEntity> {

        @Override
        public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            return UserEntity.builder()
                    .id(rs.getLong("id"))
                    .email(rs.getString("email"))
                    .nickname(rs.getString("nickname"))
                    .hashPassword(rs.getString("hash_password"))
                    .build();
        }
    }
}
