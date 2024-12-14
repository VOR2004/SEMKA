package org.example.model;

import lombok.*;

import java.sql.Timestamp;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class AnswerEntity {
    Long id;
    String text;
    String author_nick;
    Timestamp dateTime;
    Integer comment_id;
}
