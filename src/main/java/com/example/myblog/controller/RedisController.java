package com.example.myblog.controller;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class RedisController {
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @PostMapping("/add")
    public String addToRedis(String name, String value) {

        // 操作Redis中的string类型的数据,先获取ValueOperation
        ValueOperations valueOperations = redisTemplate.opsForValue();

        // 添加数据到redis
        valueOperations.set(name, value);
        return "向redis添加string类型的数据";
    }
    @GetMapping("/getk")
    public String getData(String key) {

        ValueOperations valueOperations = redisTemplate.opsForValue();
        Object v = valueOperations.get(key);
        return "key是" + key + ",它的值是:" + v;
    }
    @PostMapping("/{k}/{v}")
    public String addStringKV(@PathVariable String k,
                              @PathVariable String v) {

        // 使用StringRedisTemplate对象
        stringRedisTemplate.opsForValue().set(k,v);
        return "使用StringRedisTemplate对象添加";
    }

    @GetMapping("/{k}")
    public String getStringValue(@PathVariable String k) {

        // 获取String类型的value
        String v = stringRedisTemplate.opsForValue().get(k);
        return "从redis中通过" + k + "获取到string类型的v=" + v;
    }

}
