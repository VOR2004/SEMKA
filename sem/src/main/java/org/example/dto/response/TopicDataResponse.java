package org.example.dto.response;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TopicDataResponse {

    private Long id;
    private String nickname;
    private String title;
    private String description;
}
