package com.example.myblog.controller;


import com.example.myblog.common.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user/{user_id}/home/favorites")
@Api(tags = "个人收藏夹接口")
public class FavoritesController {


    @ApiOperation(value = "添加收藏夹")
    @PostMapping()
    public R favoriteBlog(@RequestBody String name) {
        return R.success();
    }

    @ApiOperation(value = "添加收藏")
    @PostMapping(path = "/favorites&{favorites_id}")
    public R favoriteBlog(@PathVariable int user_id, @PathVariable int favorites_id,@RequestBody int blog_id) {
        return R.success();
    }
}
