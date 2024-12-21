package org.example.dto.request;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CreateCommentRequest {
    private String text;
    private String authorNick;
    private String topic_id;
    private Integer user_id;
    private String image_id;
}
