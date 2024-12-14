package org.example.dto.response;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentDataResponse {

    private Long id;
    private String text;
    private String authorNick;
    private Integer topic_id;
}