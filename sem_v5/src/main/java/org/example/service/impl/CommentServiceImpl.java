package org.example.service.impl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.request.CreateCommentRequest;
import org.example.dto.response.CommentDataResponse;
import org.example.dto.response.OnCreateCommentResponse;
import org.example.mapper.CommentMapper;
import org.example.model.CommentEntity;
import org.example.repository.CommentRepository;
import org.example.service.CommentService;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;

    @Override
    public OnCreateCommentResponse createComment(CreateCommentRequest request) {

            if(request.getText() == null || request.getText().isBlank())
                return response(1, "Empty text", null);

            Optional<CommentEntity> optionalComment = commentRepository.createComment(commentMapper.toEntity(request));

            if(optionalComment.isEmpty())
                return response(50, "Database process error", null);

            return response(0, "OK", commentMapper.toDto(optionalComment.get()));
    }

    @Override
    public List<CommentEntity> comments(Long topicId) {
        return commentRepository.listComments(topicId);
    }


    private OnCreateCommentResponse response(int status, String statusDesc, CommentDataResponse comment) {
        return OnCreateCommentResponse.builder()
                .status(status)
                .statusDesc(statusDesc)
                .comment(comment)
                .build();
    }
}
