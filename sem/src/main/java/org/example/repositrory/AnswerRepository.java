package org.example.repositrory;

import org.example.model.AnswerEntity;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository {
    Optional<AnswerEntity> findAnswerById(Long id);

    Optional<AnswerEntity> createAnswer(AnswerEntity answer);

    List<AnswerEntity> listAnswers(Long commentId);
}
