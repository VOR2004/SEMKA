package org.example.service;

public interface LikeService {
    void leaveLike(Integer user_id, Integer post_id);

    Integer getLikesCount(Long id);

    Integer getLikesCountUser(Long id);
}
