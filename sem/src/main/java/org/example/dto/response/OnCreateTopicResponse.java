package org.example.dto.response;


import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class OnCreateTopicResponse {

    private int status;

    private String statusDesc;

    private TopicDataResponse topic;
}
