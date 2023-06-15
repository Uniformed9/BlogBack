package com.example.myblog.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myblog.entity.Blog;
import com.example.myblog.entity.Favorites;
import com.example.myblog.entity.UserFavoritesBlog;
import com.example.myblog.mapper.UserFavoritesBlogMapper;
import com.example.myblog.service.FavoritesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritesServiceImpl extends ServiceImpl<UserFavoritesBlogMapper,UserFavoritesBlog> implements FavoritesService {
    @Override
    public void insertFavorites(int userId) {

    }

    @Override
    public List<Favorites> getFavorites(int userId) {
        return null;
    }

    @Override
    public boolean modifyFavoritesDescription(int userId, int favoritesId, String description) {
        return false;
    }

    @Override
    public boolean deleteFavorites(int userId, int favoritesId) {
        return false;
    }

    @Override
    public void insertBlogToFavorites(int userId, int favoritesId, int blogId) {

    }

    @Override
    public List<Blog> getBlogs(int userId, int favoritesId) {
        return null;
    }

    @Override
    public boolean deleteBlogInFavorites(int userId, int favoritesId, int blogId) {
        return false;
    }
}
