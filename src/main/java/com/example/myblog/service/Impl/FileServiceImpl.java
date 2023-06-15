package com.example.myblog.service.Impl;

import com.example.myblog.service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Service
public class FileServiceImpl implements FileService {
    @Override
    public ResponseEntity<byte[]> download(HttpServletResponse response, String location) throws IOException {
        String fileName=Paths.get(location).getFileName().toString();
//        InputStream is = Files.newInputStream(Paths.get(location));
        FileInputStream is=new FileInputStream(new File(location));
        //创建字节数组
        byte[] bytes = new byte[is.available()];
        //将流读到字节数组中
        is.read(bytes);

        //创建HttpHeaders对象设置响应头信息
        MultiValueMap<String, String> headers = new HttpHeaders();
//        String fileName=location.substring(location.lastIndexOf(File.separator)+1);

        System.out.println(fileName);
        //设置要下载方式以及下载文件的名字
//        headers.add("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName,"UTF-8"));
        headers.add("Content-Disposition", "attachment;filename="+ new String(fileName.getBytes(StandardCharsets.UTF_8),StandardCharsets.ISO_8859_1));
        headers.add("Content-Type", MediaType.APPLICATION_OCTET_STREAM_VALUE);
        headers.add("Character-Encoding",StandardCharsets.UTF_8.name());
        //设置响应状态码
        HttpStatus statusCode = HttpStatus.OK;
        //创建ResponseEntity对象
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers,
                statusCode);
        //关闭输入流
        is.close();
        return responseEntity;
    }
    @Override
    public String upload(MultipartFile file, String Path) {
        // 1.获取当前上传的文件名
        String originalFilename = file.getOriginalFilename();
        // 2.截取当前文件名的格式后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 3.判断要存储文件的路径是否存在，不存在则创建
        File dir = new File(Path);
        if (!dir.exists()){
            dir.mkdirs();
        }
        // 4.判断该路径下是否已经存在同名字的文件，不允许重复上传同名文件
        File[] files = dir.listFiles();
        for (File f : files) {
            if (f.getName().equals(originalFilename)){
                return "文件已存在";
            }
        }
        // 5.将上传的文件保存到指定的路径
        try {
            file.transferTo(new File(Path + originalFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 6.返回数据给前端
        return originalFilename;
    }
}
