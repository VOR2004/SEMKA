package org.example.repositrory;


import org.example.model.TopicEntity;

import java.util.List;
import java.util.Optional;

public interface TopicRepository {

    Optional<TopicEntity> findTopicById(Long id);
    Optional<TopicEntity> createTopic(TopicEntity topic);
    List<TopicEntity> listAllTopics();

    void changeTopicState(Long id);

}
