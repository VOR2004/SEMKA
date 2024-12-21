package org.example.dto.response;


import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class AuthResponse {
    private int status;
    private String statusDesc;
    private UserDataResponse user;
}
