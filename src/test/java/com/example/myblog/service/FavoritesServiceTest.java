package com.example.myblog.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FavoritesServiceTest {
    @Autowired
    FavoritesService favoritesService;

    @Test
    void insertFavorites() {
    }

    @Test
    void getFavorites() {
    }

    @Test
    void updateFavoritesName() {
    }

    @Test
    void deleteFavorites() {
    }

    @Test
    void insertBlogToFavorites() {
        System.out.println(favoritesService.insertBlogToFavorites(1, 1, 12));
    }

    @Test
    void getBlogs() {
    }

    @Test
    void deleteBlogInFavorites() {
    }

    @Test
    void isFavoritesExist() {
    }

    @Test
    void testGetFavorites() {
    }
}