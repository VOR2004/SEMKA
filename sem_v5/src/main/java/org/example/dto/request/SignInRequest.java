package org.example.dto.request;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class SignInRequest {
    private String email;
    private String password;
}
