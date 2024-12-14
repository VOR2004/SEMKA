package org.example.mapper;

import org.example.dto.response.AnswerDataResponse;
import org.example.dto.request.CreateAnswerRequest;
import org.example.model.AnswerEntity;

public interface AnswerMapper {
    AnswerEntity toEntity(CreateAnswerRequest request);

    AnswerDataResponse toDto(AnswerEntity entity);
}
