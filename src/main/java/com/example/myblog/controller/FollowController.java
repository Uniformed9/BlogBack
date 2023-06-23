package com.example.myblog.controller;

import com.example.myblog.common.R;
import com.example.myblog.service.FollowRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/follow")
@Api(tags = "关注接口")
public class FollowController {

    @Autowired
    FollowRelationService followRelationService;

    @PostMapping(path = "/{follower_id}/{be_followed_id}")
    public R follow(@PathVariable int follower_id, @PathVariable int be_followed_id) {
        followRelationService.follow(follower_id, be_followed_id);
        return R.success();
    }

    @DeleteMapping(path = "/{follower_id}/{be_followed_id}")
    public R unfowllow(@PathVariable int follower_id, @PathVariable int be_followed_id) {
        followRelationService.unfollow(follower_id, be_followed_id);
        return R.success();
    }

    @ApiOperation("我的粉丝")
    @GetMapping(path = "/followers/{userId}")
    public R getFollowerList(@PathVariable int userId) {
        return R.success(followRelationService.followerList(userId));
    }

    @ApiOperation("我的关注")
    @GetMapping(path = "/myfollows/{userId}")
    public R getFollowedList(@PathVariable int userId) {
        return R.success(followRelationService.beFollowedList(userId));
    }
}
