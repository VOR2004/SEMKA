package org.example.repository;

import org.example.model.CommentEntity;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Optional<CommentEntity> findCommentById(Long id);

    Optional<CommentEntity> createComment(CommentEntity comment);

    List<CommentEntity> listComments(Long topicId);
}
