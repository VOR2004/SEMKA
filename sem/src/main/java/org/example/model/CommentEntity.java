package org.example.model;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class CommentEntity {
    Long id;
    String text;
    String author_nick;
    Timestamp dateTime;
    Integer topic_id;
    List<AnswerEntity> answers;
    String image_id;
    Integer likesCount;
    Integer user_id;
}
