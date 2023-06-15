package com.example.myblog.controller;


import com.example.myblog.common.R;
import com.example.myblog.service.FavoritesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user/{user_id}/home/favorites")
@Api(tags = "个人收藏夹接口")
public class FavoritesController {

    @Autowired
    FavoritesService favoritesService;

    @ApiOperation("获取收藏夹列表")
    @GetMapping()
    public R getFavorites(@PathVariable int user_id){
        return R.success(favoritesService.getFavorites(user_id));
    }

    @ApiOperation(value = "添加收藏夹")
    @PostMapping()
    public R addFavorite(@PathVariable int user_id, @RequestBody String name) {
        favoritesService.insertFavorites(user_id, name);
        return R.success();
    }

    @ApiOperation(value = "修改收藏夹名")
    @PutMapping(path = "/{favorites_id}")
    public R updateFavorite(@PathVariable int user_id, @PathVariable int favorites_id, @RequestBody String name) {
        favoritesService.updateFavoritesName(user_id, favorites_id, name);
        return R.success();
    }

    @ApiOperation(value = "删除收藏夹")
    @DeleteMapping()
    public R deleteFavorite(@PathVariable int user_id, @RequestBody int favorites_id) {
        return R.success(favoritesService.deleteFavorites(user_id, favorites_id));
    }

    @ApiOperation(value = "获取收藏")
    @GetMapping(path = "/{favorites_id}")
    public R getBlogsFromFavorites(@PathVariable int user_id, @PathVariable int favorites_id) {
        return R.success(favoritesService.getBlogs(user_id, favorites_id));
    }

    @ApiOperation(value = "添加收藏")
    @PostMapping(path = "/{favorites_id}")
    public R addBlogToFavorites(@PathVariable int user_id, @PathVariable int favorites_id, @RequestBody int blog_id) {
        favoritesService.insertBlogToFavorites(user_id, favorites_id, blog_id);
        return R.success();
    }

    @ApiOperation(value = "删除收藏")
    @DeleteMapping(path = "/{favorites_id}")
    public R deleteBlogFromFavorites(@PathVariable int user_id, @PathVariable int favorites_id, @RequestBody int blog_id) {
        return R.success(favoritesService.deleteBlogInFavorites(user_id, favorites_id, blog_id));
    }
}
