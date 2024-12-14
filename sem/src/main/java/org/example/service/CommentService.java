package org.example.service;

import org.example.dto.request.CreateCommentRequest;
import org.example.dto.response.OnCreateCommentResponse;
import org.example.model.CommentEntity;

import java.util.List;

public interface CommentService {

    OnCreateCommentResponse createComment(CreateCommentRequest request);

    List<CommentEntity> comments(Long topicId);

}
