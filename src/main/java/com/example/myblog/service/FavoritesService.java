package com.example.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myblog.entity.Blog;
import com.example.myblog.entity.Favorites;
import com.example.myblog.entity.UserFavoritesBlog;

import java.util.List;
import java.util.Map;

public interface FavoritesService extends IService<UserFavoritesBlog> {

    void insertFavorites(int userId, String name);

    List<Favorites> getFavorites(int userId);

    boolean updateFavoritesName(int userId, int favoritesId, String name);

    boolean deleteFavorites(int userId, int favoritesId);

    boolean insertBlogToFavorites(int userId, int favoritesId, int blogId);

    List<Blog> getBlogs(int userId, int favoritesId);

    boolean deleteBlogInFavorites(int userId, int favoritesId, int blogId);

    boolean isFavoritesExist(int userId, int favoritesId);

    Favorites getFavorites(int userId, int favoritesId);

    Map<Integer,List<Blog>> getAllBlogsInFavorites(int userId);
}
