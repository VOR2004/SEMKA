package org.example.service;

import org.example.dto.request.CreateTopicRequest;
import org.example.dto.response.OnCreateTopicResponse;
import org.example.model.TopicEntity;

import java.util.List;
import java.util.Optional;

public interface TopicService {
    OnCreateTopicResponse createTopic(CreateTopicRequest request);

    List<TopicEntity> listTopic();

    void closeOpen(Long id);

    Optional<TopicEntity> getTopic(Long id);
}
