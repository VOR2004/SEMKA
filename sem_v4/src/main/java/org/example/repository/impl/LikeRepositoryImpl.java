package org.example.repository.impl;

import lombok.RequiredArgsConstructor;
import org.example.model.LikeEntity;
import org.example.repository.LikeRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikeRepository {

    private final JdbcTemplate jdbcTemplate;

    private final LikeRepositoryImpl.LikeRowMapper likeRowMapper = new LikeRepositoryImpl.LikeRowMapper();

    private static final String SQL_INSERT = "insert into likes(user_id, post_id) values (?, ?)";
    private final static String SQL_SELECT_BY_ID = "select * from likes where id = ?";

    private final static String SQL_SELECT_BY_ID_MANY = "select * from likes where post_id = ? and user_id = ?";
    private final static String SQL_COUNT_LIKES = "select COUNT(*) from likes where post_id = ?";

    private final static String SQL_COUNT_LIKES_OF_USER = "select COUNT(*) from likes where user_id = ?";
    private final static String SQL_DELETE_LIKE = "delete from likes where post_id = ? and user_id = ?";


    @Override
    public Optional<LikeEntity> findLikeById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[] {id}, likeRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<LikeEntity> findLikeByIds(Long user_id, Long post_id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID_MANY, likeRowMapper, post_id, user_id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Integer countLikesOfUser(Long id) {
        try {
            return (jdbcTemplate.queryForObject(SQL_COUNT_LIKES_OF_USER, Integer.class, id));
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    @Override
    public Integer countLikes(Long id) {
        try {
            return (jdbcTemplate.queryForObject(SQL_COUNT_LIKES, Integer.class, id));
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }



    @Override
    public void leaveLike(Integer user_id, Integer post_id) {

            KeyHolder holder = new GeneratedKeyHolder();
            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(SQL_INSERT, new String[] {"id"});
                ps.setInt(1, user_id);
                ps.setInt(2, post_id);
                return ps;
            }, holder);
    }

    @Override
    public void deleteLike(Integer user_id, Integer post_id) {
        jdbcTemplate.update(SQL_DELETE_LIKE, post_id, user_id);
    }

    private static final class LikeRowMapper implements RowMapper<LikeEntity> {

        @Override
        public LikeEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            return LikeEntity.builder()
                    .id(rs.getLong("id"))
                    .user_id(rs.getLong("user_id"))
                    .post_id(rs.getLong("post_id"))
                    .build();
        }
    }
}
