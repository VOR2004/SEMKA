package org.example.mapper.impl;

import org.example.dto.request.CreateAnswerRequest;
import org.example.dto.response.AnswerDataResponse;
import org.example.mapper.AnswerMapper;
import org.example.model.AnswerEntity;

public class AnswerMapperImpl implements AnswerMapper {

    @Override
    public AnswerEntity toEntity(CreateAnswerRequest request) {
        return AnswerEntity.builder()
                .text(request.getText())
                .author_nick(request.getAuthorNick())
                .comment_id(Integer.valueOf(request.getComment_id()))
                .build();
    }

    @Override
    public AnswerDataResponse toDto(AnswerEntity entity) {
        return AnswerDataResponse.builder()
                .id(entity.getId())
                .text(entity.getText())
                .authorNick(entity.getAuthor_nick())
                .comment_id(entity.getComment_id())
                .build();
    }
}
