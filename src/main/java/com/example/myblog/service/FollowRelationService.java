package com.example.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myblog.entity.FollowRelation;
import com.example.myblog.entity.User;

import java.util.List;

public interface FollowRelationService extends IService<FollowRelation> {
    void follow(int followerId, int beFollowedId);

    void unfollow(int followerId, int beFollowedId);

    /**
     * 我的粉丝
     * @param userId
     * @return
     */
    List<User> followerList(int userId);

    /**
     * 我的关注
     * @param userId
     * @return
     */
    List<User> beFollowedList(int userId);
}
