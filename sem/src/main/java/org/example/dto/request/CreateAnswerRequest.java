package org.example.dto.request;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CreateAnswerRequest {
    private String text;
    private String authorNick;
    private String comment_id;
}
