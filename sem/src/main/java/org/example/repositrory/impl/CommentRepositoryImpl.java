package org.example.repositrory.impl;

import lombok.RequiredArgsConstructor;
import org.example.model.AnswerEntity;
import org.example.model.CommentEntity;
import org.example.repositrory.CommentRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {



    private final JdbcTemplate jdbcTemplate;

    private final CommentRepositoryImpl.CommentRowMapper commentRowMapper = new CommentRepositoryImpl.CommentRowMapper();

    private final CommentRepositoryImpl.AnswerRowMapper answerRowMapper = new CommentRepositoryImpl.AnswerRowMapper();

    private static final String SQL_INSERT = "insert into comments(text, author_nick, date_time, topic_id, user_id, image_id) values (?, ?, ?, ?, ?, ?)";

    private final static String SQL_SELECT_BY_ID = "select * from comments where id = ?";

    private static final String SQL_INSERT_IMAGE = "update comments set image_id = ? where id = ?";

    private final static String SQL_SELECT_BY_TOPIC_ID = "select * from comments where topic_id = ?";

    private final static String SQL_SELECT_BY_COMMENT_ID = "select * from answers where comment_id = ?";

    private final static String SQL_COUNT_LIKES = "select COUNT(*) from likes where post_id = ?";



    @Override
    public Optional<CommentEntity> findCommentById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, commentRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }


    @Override
    public Optional<CommentEntity> createComment(CommentEntity comment) {
        java.util.Date date = new java.util.Date();
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(SQL_INSERT, new String[] {"id"});
            ps.setString(1, comment.getText());
            ps.setString(2, comment.getAuthor_nick());
            ps.setTimestamp(3, new Timestamp(date.getTime()));
            ps.setInt(4, comment.getTopic_id());
            ps.setInt(5, comment.getUser_id());
            ps.setString(6, comment.getImage_id());
            return ps;
        }, holder);
        Long id = Objects.requireNonNull(holder.getKey()).longValue();
        return findCommentById(id);
    }



    @Override
    public List<CommentEntity> listComments(Long topicId) {
        List<CommentEntity> l = jdbcTemplate.query(SQL_SELECT_BY_TOPIC_ID, commentRowMapper, topicId);
        for(CommentEntity c: l) {
            c.setAnswers(jdbcTemplate.query(SQL_SELECT_BY_COMMENT_ID, answerRowMapper, c.getId()));
            c.setLikesCount(jdbcTemplate.queryForObject(SQL_COUNT_LIKES, Integer.class, c.getId()));
        }
        return l;
    }

    private static final class CommentRowMapper implements RowMapper<CommentEntity> {

        @Override
        public CommentEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            return CommentEntity.builder()
                    .id(rs.getLong("id"))
                    .text(rs.getString("text"))
                    .author_nick(rs.getString("author_nick"))
                    .dateTime(rs.getTimestamp("date_time"))
                    .user_id(rs.getInt("user_id"))
                    .image_id(rs.getString("image_id"))
                    .build();
        }
    }

    private static final class AnswerRowMapper implements RowMapper<AnswerEntity> {

        @Override
        public AnswerEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            return AnswerEntity.builder()
                    .id(rs.getLong("id"))
                    .text(rs.getString("text"))
                    .author_nick(rs.getString("author_nick"))
                    .dateTime(rs.getTimestamp("date_time"))
                    .build();
        }
    }
}
