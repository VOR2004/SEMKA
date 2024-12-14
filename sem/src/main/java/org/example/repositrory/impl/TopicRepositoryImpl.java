package org.example.repositrory.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.TopicEntity;
import org.example.repositrory.TopicRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

@Slf4j
@RequiredArgsConstructor

public class TopicRepositoryImpl implements TopicRepository {

    private final JdbcTemplate jdbcTemplate;

    private final TopicRepositoryImpl.TopicRowMapper topicRowMapper = new TopicRepositoryImpl.TopicRowMapper();


    private final static String SQL_SELECT_ALL = "select * from topics_data order by created_time desc";

    private final static String SQL_SELECT_BY_ID = "select * from topics_data where id = ?";

    private static final String SQL_CHANGE_TOPIC_STATE = "update topics_data set is_closed = (NOT is_closed) where id = ?";

    private static final String SQL_INSERT = "insert into topics_data(title, description, nick, created_time) values (?, ?, ?, ?)";

    @Override
    public Optional<TopicEntity> findTopicById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, topicRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void changeTopicState(Long id) {
        jdbcTemplate.update(SQL_CHANGE_TOPIC_STATE, id);
    }


    @Override
    public Optional<TopicEntity> createTopic(TopicEntity topic) {
        try {
            java.util.Date date = new java.util.Date();
            KeyHolder holder = new GeneratedKeyHolder();
            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(SQL_INSERT, new String[] {"id"});
                ps.setString(3, topic.getNickname());
                ps.setString(1, topic.getTitle());
                ps.setString(2, topic.getDescription());
                ps.setTimestamp(4, new Timestamp(date.getTime()));

                return ps;
            }, holder);
            Long id = Objects.requireNonNull(holder.getKey()).longValue();
            return findTopicById(id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<TopicEntity> listAllTopics() {
        try {
            return (jdbcTemplate.query(SQL_SELECT_ALL, (rs, rowNum) -> new TopicEntity(
                    rs.getLong("id"),
                    rs.getString("nick"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getTimestamp("created_time"),
                    rs.getBoolean("is_closed")
                    )
                )
            );
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    private static final class TopicRowMapper implements RowMapper<TopicEntity> {

        @Override
        public TopicEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            return TopicEntity.builder()
                    .id(rs.getLong("id"))
                    .nickname(rs.getString("nick"))
                    .title(rs.getString("title"))
                    .description(rs.getString("description"))
                    .createdTime(rs.getTimestamp("created_time"))
                    .isClosed(rs.getBoolean("is_closed"))
                    .build();
        }
    }
}
