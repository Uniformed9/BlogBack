package com.example.myblog.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myblog.entity.FollowRelation;
import com.example.myblog.entity.User;
import com.example.myblog.mapper.FollowRelationMapper;
import com.example.myblog.mapper.UserMapper;
import com.example.myblog.service.FollowRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowRelationImpl extends ServiceImpl<FollowRelationMapper, FollowRelation> implements FollowRelationService {
    @Autowired
    FollowRelationMapper followRelationMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public void follow(int followerId, int beFollowedId) {
        LambdaQueryWrapper<FollowRelation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FollowRelation::getBeFollowedId, beFollowedId);
        wrapper.eq(FollowRelation::getFollowerId, followerId);
        FollowRelation relation = followRelationMapper.selectOne(wrapper);
        if (relation != null) return;
        followRelationMapper.insert(new FollowRelation(beFollowedId, followerId));
    }

    @Override
    public void unfollow(int followerId, int beFollowedId) {
        LambdaQueryWrapper<FollowRelation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FollowRelation::getBeFollowedId, beFollowedId);
        wrapper.eq(FollowRelation::getFollowerId, followerId);
        followRelationMapper.delete(wrapper);
    }

    @Override
    public List<User> followerList(int userId) {
        LambdaQueryWrapper<FollowRelation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FollowRelation::getBeFollowedId, userId);
        List<FollowRelation> relations = followRelationMapper.selectList(wrapper);
        List<User> res = new ArrayList<>();
        for (FollowRelation relation : relations) {
            res.add(userMapper.selectById(relation.getFollowerId()));
        }
        return res;
    }

    @Override
    public List<User> beFollowedList(int userId) {
        LambdaQueryWrapper<FollowRelation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FollowRelation::getFollowerId, userId);
        List<FollowRelation> relations = followRelationMapper.selectList(wrapper);
        List<User> res = new ArrayList<>();
        for (FollowRelation relation : relations) {
            res.add(userMapper.selectById(relation.getBeFollowedId()));
        }
        return res;
    }
}
