package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.repositrory.LikeRepository;
import org.example.service.LikeService;

@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;


    @Override
    public void leaveLike(Integer user_id, Integer post_id) {
        if (likeRepository.findLikeByIds(user_id.longValue(), post_id.longValue()).isEmpty()) {
            likeRepository.leaveLike(user_id, post_id);
        } else {
            likeRepository.deleteLike(user_id, post_id);
        }
    }

    @Override
    public Integer getLikesCount(Long id) {
        return likeRepository.countLikes(id);
    }

    @Override
    public Integer getLikesCountUser(Long id) {
        return likeRepository.countLikesOfUser(id);
    }
}
