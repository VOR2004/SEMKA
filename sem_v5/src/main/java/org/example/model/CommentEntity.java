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
    private Long id;
    private String text;
    private String author_nick;
    private Timestamp dateTime;
    private Integer topic_id;
    private List<AnswerEntity> answers;
    private String image_id;
    private Integer likesCount;
    private Integer user_id;
}
