package com.example.myblog.service.Impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myblog.dto.UserDto;
import com.example.myblog.entity.User;
import com.example.myblog.exception.HaveDisabledException;
import com.example.myblog.exception.PasswordWrongException;
import com.example.myblog.exception.SameNameException;
import com.example.myblog.mapper.UserMapper;
import com.example.myblog.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.example.myblog.util.RedisUtil.LOGIN_USER_KEY;
import static com.example.myblog.util.RedisUtil.LOGIN_USER_TTL;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserMapper userMapper;
    @Value("E:/code/myblog/source/user/")
    private String UserPath;

    public User login(@RequestBody Map<String,String> map, HttpSession session){
        String userName = map.get("userName");
        String password = map.get("password");
        String nickname=map.get("nickname");
        String email=map.get("email");
        String avatar=map.get("avatar");
        //查看该用户是否为新用户
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUserName,userName);
        User user = getOne(userLambdaQueryWrapper);
        if(user==null){
            //是新用户,自动注册
            user = new User();
            user.setUserName(userName);
            user.setPassword(password);
            user.setStatus(1);
            user.setNickName(nickname);
            user.setAvatar(avatar);
            user.setEmail(email);
            save(user);
            //将用户的信息存到session中，这样可以通过过滤器
            //随机生成token作为登录令牌
            String token = UUID.randomUUID().toString();

            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setToken(token);
            userDto.setUserName(userName);
            userDto.setPassword(password);
            userDto.setStatus(1);

            Map<String, Object> userMap = BeanUtil.beanToMap(userDto, new HashMap<>(),
                    CopyOptions.create()
                            .setIgnoreNullValue(true)
                            //在setFieldValueEditor中也需要判空
                            .setFieldValueEditor((fieldName,fieldValue) -> {
                                if (fieldValue == null){
                                    fieldValue = "0";
                                }else {
                                    fieldValue = fieldValue + "";
                                }
                                return fieldValue;
                            }));
            //存储
            stringRedisTemplate.opsForHash().putAll(LOGIN_USER_KEY+token,userMap);
            //设置有效期
            stringRedisTemplate.expire(LOGIN_USER_KEY+token,LOGIN_USER_TTL, TimeUnit.MINUTES);
            //在用户文件夹下创建一个和用户名字同名的文件夹
            String userDir = UserPath+userName;
            java.io.File file = new java.io.File(userDir);
            if(!file.exists()){
                file.mkdirs();
            }

            return userDto;
        }else{
            if(user.getStatus()==0){

                throw new HaveDisabledException("用户已被禁用");
            }else if(!Objects.equals(user.getPassword(), password)){
                throw new PasswordWrongException("密码错误");

            }else{
                //将用户的信息存到session中，这样可以通过过滤器
                //随机生成token作为登录令牌
                String token = UUID.randomUUID().toString();
                session.setAttribute(token,user);
                UserDto userDto = new UserDto();
                userDto.setId(user.getId());
                userDto.setToken(token);
                userDto.setUserName(user.getUserName());
                userDto.setPassword(user.getPassword());
                userDto.setStatus(user.getStatus());

                userDto.setAvatar(user.getAvatar());
                userDto.setNickName(user.getNickName());
                userDto.setEmail(user.getEmail());

                Map<String, Object> userMap = BeanUtil.beanToMap(userDto, new HashMap<>(),
                        CopyOptions.create()
                                .setIgnoreNullValue(true)
                                //在setFieldValueEditor中也需要判空
                                .setFieldValueEditor((fieldName,fieldValue) -> {
                                    if (fieldValue == null){
                                        fieldValue = "0";
                                    }else {
                                        fieldValue = fieldValue + "";
                                    }
                                    return fieldValue;
                                }));
                //存储
                stringRedisTemplate.opsForHash().putAll(LOGIN_USER_KEY+token,userMap);
                //设置有效期
                stringRedisTemplate.expire(LOGIN_USER_KEY+token,LOGIN_USER_TTL, TimeUnit.MINUTES);
                return userDto;
            }
        }
    }
    //上传头像文件
    public User Register(@RequestBody Map<String,String> map,MultipartFile avatar){
        String userName = map.get("userName");
        String password = map.get("password");
        String nickname=map.get("nickname");
        String email=map.get("email");
//        String avatar=map.get("avatar");

        LambdaQueryWrapper<User> usernameLambdaQueryWrapper = new LambdaQueryWrapper<>();
        usernameLambdaQueryWrapper.eq(User::getUserName,userName);

        LambdaQueryWrapper<User> nicknameLambdaQueryWrapper=new LambdaQueryWrapper<>();
        nicknameLambdaQueryWrapper.eq(User::getNickName,nickname);
        //用户名，昵称已被占用，无法注册
        if( getOne(usernameLambdaQueryWrapper)!=null){
            throw new SameNameException("用户名已存在");
        }
        if(getOne(nicknameLambdaQueryWrapper)!=null){
            throw  new SameNameException("昵称已存在");
        }
        //开始注册

        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setStatus(1);
        user.setNickName(nickname);
        //    user.setAvatar(avatar);
        user.setEmail(email);
        //创建文件夹，把头像放在里面

        String userDir = UserPath+userName;
        java.io.File file = new java.io.File(userDir);
        if(!file.exists()){
            file.mkdirs();
        }
        String originalFilename=avatar.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String filepath=originalFilename+suffix;
        File srcFile=new File(userDir+filepath);
        try {
            //把前端传送的文件保存在本地中
            avatar.transferTo(srcFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        user.setAvatar(userDir+filepath);

        save(user);
        return user;
    }
    @Override
    public Map<Integer, User> getUserMap() {
        return userMapper.getUserMap();
    }

    @Override
    public User selectUserByuserId(int id) {
        return userMapper.selectById(id);
    }
}
