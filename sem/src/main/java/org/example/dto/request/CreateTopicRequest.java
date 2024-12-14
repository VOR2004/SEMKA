package org.example.dto.request;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CreateTopicRequest {
    private String nickname;
    private String title;
    private String description;
}
