package org.example.dto.response;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDataResponse {
    private Long id;
    private String text;
    private String authorNick;
    private Integer comment_id;
}
