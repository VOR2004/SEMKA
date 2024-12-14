package org.example.dto.response;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class OnCreateCommentResponse {
    private int status;

    private String statusDesc;

    private CommentDataResponse comment;
}
