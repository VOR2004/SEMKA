package org.example.model;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LikeEntity {
    private Long id;
    private Long user_id;
    private Long post_id;
}
