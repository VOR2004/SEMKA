package org.example.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.request.CreateAnswerRequest;
import org.example.dto.response.AnswerDataResponse;
import org.example.dto.response.OnCreateAnswerResponse;
import org.example.mapper.AnswerMapper;
import org.example.model.AnswerEntity;
import org.example.repository.AnswerRepository;
import org.example.service.AnswerService;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    private final AnswerMapper answerMapper;

    @Override
    public OnCreateAnswerResponse createAnswer(CreateAnswerRequest request) {

        if(request.getText() == null || request.getText().isBlank())
            return response(1, "Empty text", null);

        Optional<AnswerEntity> optionalAnswer = answerRepository.createAnswer(answerMapper.toEntity(request));

        if(optionalAnswer.isEmpty())
            return response(50, "Database process error", null);

        return response(0, "OK", answerMapper.toDto(optionalAnswer.get()));
    }


    private OnCreateAnswerResponse response(int status, String statusDesc, AnswerDataResponse answer) {
        return OnCreateAnswerResponse.builder()
                .status(status)
                .statusDesc(statusDesc)
                .answer(answer)
                .build();
    }
}
