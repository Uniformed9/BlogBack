package com.example.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myblog.entity.Blog;
import com.example.myblog.entity.Favorites;
import com.example.myblog.entity.UserFavoritesBlog;

import java.util.List;

public interface FavoritesService extends IService<UserFavoritesBlog> {

    void insertFavorites(int userId);

    List<Favorites> getFavorites(int userId);

    boolean modifyFavoritesDescription(int userId, int favoritesId, String description);

    boolean deleteFavorites(int userId, int favoritesId);

    void insertBlogToFavorites(int userId, int favoritesId, int blogId);

    List<Blog> getBlogs(int userId, int favoritesId);

    boolean deleteBlogInFavorites(int userId, int favoritesId, int blogId);


}
