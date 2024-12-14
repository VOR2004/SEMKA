package org.example.model;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    private Long id;

    private String email;

    private String hashPassword;

    private String nickname;

    private String token;

    private Integer tokenUsage;

    private Integer likesCount;

    private String avatarUuid;

}
