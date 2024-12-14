package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.request.CreateTopicRequest;
import org.example.dto.response.OnCreateTopicResponse;
import org.example.dto.response.TopicDataResponse;
import org.example.mapper.TopicMapper;
import org.example.model.TopicEntity;
import org.example.repositrory.TopicRepository;
import org.example.service.TopicService;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    private final TopicMapper topicMapper;

    @Override
    public OnCreateTopicResponse createTopic(CreateTopicRequest request) {
        if(request.getTitle() == null || request.getTitle().isBlank())
            return response(1, "Empty title", null);

        if(request.getDescription() == null || request.getDescription().isBlank())
            return response(2, "Empty desc", null);

        Optional<TopicEntity> optionalTopic = topicRepository.createTopic(topicMapper.toEntity(request));

        if(optionalTopic.isEmpty())
            return response(50, "Database error", null);

        return response(0, "OK", topicMapper.toDto(optionalTopic.get()));
    }

    @Override
    public List<TopicEntity> listTopic() {
        return topicRepository.listAllTopics();
    }

    @Override
    public void closeOpen(Long id) {
        topicRepository.changeTopicState(id);
    }

    @Override
    public Optional<TopicEntity> getTopic(Long id) {
        return topicRepository.findTopicById(id);
    }


    private OnCreateTopicResponse response(int status, String statusDesc, TopicDataResponse topic) {
        return OnCreateTopicResponse.builder()
                .status(status)
                .statusDesc(statusDesc)
                .topic(topic)
                .build();
    }
}
