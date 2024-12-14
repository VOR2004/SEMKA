package org.example.dto.response;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDataResponse {

    private Long id;

    private String email;

    private String nickname;

}
