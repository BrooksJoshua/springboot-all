package com.boy.springbootallcache.service;


import com.boy.springbootallcache.entity.User;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Cacheable(cacheNames = "cache1", key = "#id") // 默认情况下只有当该方法被用完全一致的参数调用多次时, 从第二次开始就会从缓存中查询. 但是如果加上key属性, 可以自定义只将其中的某一个参数作为缓存key, 即当改参数一样时, 即可作为缓存, 不要求其他参数的一致性
    public User getUserById(Integer id){
        User u=new User(1,"刘德华","爱你一万年");
        System.out.println("u");
        return  u;
    }


    @CachePut(cacheNames = "cache2",key="#id")
    public int updateUserById(Integer id){
        System.out.println("update cache with @CachePut");
        
        return 1;

    }

}
