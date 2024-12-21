package org.example.model;

import lombok.*;

import java.sql.Timestamp;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class AnswerEntity {
    private Long id;
    private String text;
    private String author_nick;
    private Timestamp dateTime;
    private Integer comment_id;
}
