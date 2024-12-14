package org.example.dto.response;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class OnCreateAnswerResponse {
    private int status;

    private String statusDesc;

    private AnswerDataResponse answer;
}
