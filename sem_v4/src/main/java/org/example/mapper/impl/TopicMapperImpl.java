package org.example.mapper.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.request.CreateTopicRequest;
import org.example.dto.response.TopicDataResponse;
import org.example.mapper.TopicMapper;
import org.example.model.TopicEntity;

@Slf4j
public class TopicMapperImpl implements TopicMapper {

    @Override
    public TopicEntity toEntity(CreateTopicRequest request) {
        return TopicEntity.builder()
                .nickname(request.getNickname())
                .title(request.getTitle())
                .description(request.getDescription())
                .build();
    }

    @Override
    public TopicDataResponse toDto(TopicEntity entity) {
        return TopicDataResponse.builder()
                .id(entity.getId())
                .nickname(entity.getNickname())
                .description(entity.getDescription())
                .title(entity.getTitle())
                .build();
    }
}

