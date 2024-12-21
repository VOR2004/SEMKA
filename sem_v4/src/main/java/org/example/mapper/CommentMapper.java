package org.example.mapper;

import org.example.dto.response.CommentDataResponse;
import org.example.dto.request.CreateCommentRequest;
import org.example.model.CommentEntity;

public interface CommentMapper {
    CommentEntity toEntity(CreateCommentRequest request);

    CommentDataResponse toDto(CommentEntity entity);
}
