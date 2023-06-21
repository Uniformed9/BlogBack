package com.example.myblog.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myblog.entity.Blog;
import com.example.myblog.entity.Favorites;
import com.example.myblog.entity.User;
import com.example.myblog.entity.UserFavoritesBlog;
import com.example.myblog.mapper.BlogMapper;
import com.example.myblog.mapper.FavoritesMapper;
import com.example.myblog.mapper.UserFavoritesBlogMapper;
import com.example.myblog.mapper.UserMapper;
import com.example.myblog.service.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FavoritesServiceImpl extends ServiceImpl<UserFavoritesBlogMapper, UserFavoritesBlog> implements FavoritesService {

    @Autowired
    BlogMapper blogMapper;
    @Autowired
    FavoritesMapper favoritesMapper;
    @Autowired
    UserFavoritesBlogMapper userFavoritesBlogMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public Favorites getFavorites(int userId, int favoritesId) {
        LambdaQueryWrapper<Favorites> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Favorites::getUserId, userId);
        wrapper.eq(Favorites::getId, favoritesId);
        Favorites f = favoritesMapper.selectOne(wrapper);
        if (f == null) return null;
        else return f;
    }

    @Override
    public boolean isFavoritesExist(int userId, int favoritesId) {
        if (getFavorites(userId, favoritesId) == null) return false;
        else return true;
    }

    @Override
    public void insertFavorites(int userId, String name) {
        Favorites f = new Favorites();
        f.setUserId(userId);
        f.setName(name);
        favoritesMapper.insert(f);
    }

    @Override
    public List<Favorites> getFavorites(int userId) {
        LambdaQueryWrapper<Favorites> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Favorites::getUserId, userId);
        List<Favorites> fl = favoritesMapper.selectList(wrapper);
        if (fl == null || fl.size() == 0) return null;
        return fl;
    }

    @Override
    public boolean updateFavoritesName(int userId, int favoritesId, String name) {
        Favorites f = getFavorites(userId, favoritesId);
        if (f == null) return false;
        f.setName(name);
        favoritesMapper.updateById(f);
        return true;
    }

    @Override
    public boolean deleteFavorites(int userId, int favoritesId) {
        Favorites f = getFavorites(userId, favoritesId);
        if (f == null) return false;
        favoritesMapper.deleteById(favoritesId);
        LambdaQueryWrapper<UserFavoritesBlog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserFavoritesBlog::getFavoritesId, favoritesId);
        userFavoritesBlogMapper.delete(wrapper);
        return true;
    }

    @Override
    public Map<Integer, List<Blog>> getAllBlogsInFavorites(int userId) {
        Map<Integer, List<Blog>> res = new HashMap<>();
        List<Favorites> l = getFavorites(userId);
        for (Favorites f : l) {
            res.put(f.getId(), getBlogs(userId, f.getId()));
        }
        return res;
    }

    @Override
    public boolean insertBlogToFavorites(int userId, int favoritesId, int blogId) {
        boolean canInsert = true;
        if (!userMapper.exists(new LambdaQueryWrapper<User>().eq(User::getId, userId)))
            canInsert = false;
        if (!favoritesMapper.exists(new LambdaQueryWrapper<Favorites>().eq(Favorites::getId, favoritesId)))
            canInsert = false;
        if (!blogMapper.exists(new LambdaQueryWrapper<Blog>().eq(Blog::getId, blogId)))
            canInsert = false;
        LambdaQueryWrapper<UserFavoritesBlog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserFavoritesBlog::getUserId, userId);
        wrapper.eq(UserFavoritesBlog::getFavoritesId, favoritesId);
        wrapper.eq(UserFavoritesBlog::getBlogId, blogId);
        if (userFavoritesBlogMapper.selectList(wrapper).size() > 0)
            canInsert = false;
        if (!canInsert) return false;
        UserFavoritesBlog ufb = new UserFavoritesBlog();
        ufb.setUserId(userId);
        ufb.setFavoritesId(favoritesId);
        ufb.setBlogId(blogId);
        userFavoritesBlogMapper.insert(ufb);
        return true;
    }

    @Override
    public List<Blog> getBlogs(int userId, int favoritesId) {
        List<Blog> ret = new ArrayList<>();
        LambdaQueryWrapper<UserFavoritesBlog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserFavoritesBlog::getUserId, userId);
        wrapper.eq(UserFavoritesBlog::getFavoritesId, favoritesId);
        List<UserFavoritesBlog> l = userFavoritesBlogMapper.selectList(wrapper);
        if (l == null || l.size() == 0) return null;
        for (UserFavoritesBlog ufb : l)
            ret.add(blogMapper.selectOne(new LambdaQueryWrapper<Blog>().eq(Blog::getId, ufb.getBlogId())));
        return ret;
    }

    @Override
    public boolean deleteBlogInFavorites(int userId, int favoritesId, int blogId) {
        LambdaQueryWrapper<UserFavoritesBlog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserFavoritesBlog::getUserId, userId);
        wrapper.eq(UserFavoritesBlog::getFavoritesId, favoritesId);
        wrapper.eq(UserFavoritesBlog::getBlogId, blogId);
        UserFavoritesBlog ufb = userFavoritesBlogMapper.selectOne(wrapper);
        if (ufb == null) return false;
        userFavoritesBlogMapper.deleteById(ufb);
        return true;
    }
}
