package com.example.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService  {
    String upload(MultipartFile file, String Path);
    ResponseEntity<byte[]> download(HttpServletResponse response, String location) throws IOException;
}
