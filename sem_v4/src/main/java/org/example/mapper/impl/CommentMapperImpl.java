package org.example.mapper.impl;

import org.example.dto.response.CommentDataResponse;
import org.example.dto.request.CreateCommentRequest;
import org.example.mapper.CommentMapper;
import org.example.model.CommentEntity;

public class CommentMapperImpl implements CommentMapper {

    @Override
    public CommentEntity toEntity(CreateCommentRequest request) {
        return CommentEntity.builder()
                .text(request.getText())
                .author_nick(request.getAuthorNick())
                .topic_id(Integer.valueOf(request.getTopic_id()))
                .user_id(request.getUser_id())
                .image_id(request.getImage_id())
                .build();
    }

    @Override
    public CommentDataResponse toDto(CommentEntity entity) {
        return CommentDataResponse.builder()
                .id(entity.getId())
                .text(entity.getText())
                .authorNick(entity.getAuthor_nick())
                .topic_id(entity.getTopic_id())
                .build();
    }
}
