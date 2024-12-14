package org.example.repositrory.impl;

import lombok.RequiredArgsConstructor;
import org.example.model.AnswerEntity;
import org.example.repositrory.AnswerRepository;
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
public class AnswerRepositoryImpl implements AnswerRepository {

    private final JdbcTemplate jdbcTemplate;

    private final AnswerRepositoryImpl.AnswerRowMapper answerRowMapper = new AnswerRepositoryImpl.AnswerRowMapper();

    private static final String SQL_INSERT = "insert into answers(text, author_nick, date_time, comment_id) values (?, ?, ?, ?)";

    private final static String SQL_SELECT_BY_ID = "select * from answers where id = ?";

    private final static String SQL_SELECT_BY_COMMENT_ID = "select * from answers where comment_id = ?";



    @Override
    public Optional<AnswerEntity> findAnswerById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, answerRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<AnswerEntity> createAnswer(AnswerEntity comment) {
        java.util.Date date = new java.util.Date();
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(SQL_INSERT, new String[] {"id"});
            ps.setString(1, comment.getText());
            ps.setString(2, comment.getAuthor_nick());
            ps.setTimestamp(3, new Timestamp(date.getTime()));
            ps.setInt(4, comment.getComment_id());
            return ps;
        }, holder);
        Long id = Objects.requireNonNull(holder.getKey()).longValue();
        return findAnswerById(id);
    }

    @Override
    public List<AnswerEntity> listAnswers(Long commentId) {
        return jdbcTemplate.query(SQL_SELECT_BY_COMMENT_ID, answerRowMapper, commentId);
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
