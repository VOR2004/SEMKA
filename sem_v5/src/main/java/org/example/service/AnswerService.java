package org.example.service;

import org.example.dto.request.CreateAnswerRequest;
import org.example.dto.response.OnCreateAnswerResponse;

public interface AnswerService {
    OnCreateAnswerResponse createAnswer(CreateAnswerRequest request);

}
