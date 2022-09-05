package com.boy.springbootallredis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-09-05 15:31x
 */
@Service
public class RedisService {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 设置k-v并指定有效期。
     *
     * @param key               键
     * @param val               值
     * @param expDurationSecond 有效期 单位秒
     * @return 成功赋值键值对， 并设置有效期， 则返回TRUE， 否则false。
     */
    public boolean setEx(String key, String val, Long expDurationSecond) {
        boolean flag = false;
        try {
            ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
            ops.set(key, val);
            stringRedisTemplate.expire(key, Duration.ofSeconds(expDurationSecond)); //设置半小时有效
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 判断redis中是否存在某个键值对
     *
     * @param key
     * @return 根绝key是否查询到对应值， 有结果则为true， 否则false。
     */
    public boolean exists(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    /**
     * 根据key删除键值对， 如果没有该key， 返回false。
     *
     * @param key
     * @return
     */
    public boolean remove(String key) {
        if (exists(key)) {
            System.out.println("...........removing.......... key = " + key);
            return stringRedisTemplate.delete(key);
        }
        return false;
    }


}
