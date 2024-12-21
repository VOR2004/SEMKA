package org.example.model;

import lombok.*;

import java.sql.Timestamp;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TopicEntity {
    private Long id;
    private String nickname;
    private String title;
    private String description;
    private Timestamp createdTime;
    private Boolean isClosed;
}
