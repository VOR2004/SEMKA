package org.example.repositrory;

import org.example.model.LikeEntity;

import java.util.Optional;

public interface LikeRepository {

    Optional<LikeEntity> findLikeById(Long id);

    Optional<LikeEntity> findLikeByIds(Long user_id, Long post_id);

    Integer countLikesOfUser(Long id);

    Integer countLikes(Long id);

    void leaveLike(Integer user_id, Integer post_id);

    void deleteLike(Integer user_id, Integer post_id);
}
