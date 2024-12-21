package org.example.util;

import org.example.model.CommentEntity;
import org.example.model.TopicEntity;
import org.example.service.CommentService;
import org.example.service.TopicService;

import java.util.List;


public class ReturnObjUtils {

    public static List<TopicEntity> listTopic(TopicService topicService) {
        return topicService.listTopic();
    }

    public static List<CommentEntity> listComment(CommentService commentService, Long idTopic) {
        return commentService.comments(idTopic);
    }

}
