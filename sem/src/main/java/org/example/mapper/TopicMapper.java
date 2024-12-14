package org.example.mapper;

import org.example.dto.request.CreateTopicRequest;
import org.example.dto.response.TopicDataResponse;
import org.example.model.TopicEntity;

public interface TopicMapper {

    TopicEntity toEntity(CreateTopicRequest request);

    TopicDataResponse toDto(TopicEntity entity);

}
