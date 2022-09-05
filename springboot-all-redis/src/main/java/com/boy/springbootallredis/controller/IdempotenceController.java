package com.boy.springbootallredis.controller;

import com.boy.springbootallredis.anno.MyIdempotenceEnabled;
import com.boy.springbootallredis.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-09-05 16:06
 */
@RestController
public class IdempotenceController {
    @Autowired
    TokenService tokenService;

    /**
     * 获取token， 并设置有效期，单位秒， 没设置时默认为半小时。
     * @param expDurationSecond
     * @return
     */
    @GetMapping("/getToken")
    public String getToken(@RequestParam(required = false) Long expDurationSecond){
        if(StringUtils.isEmpty(expDurationSecond)){
            expDurationSecond = 1800L;
        }
        return tokenService.getInstanceAndSetExpire(expDurationSecond);
    }

    /**
     * 无需开启
     * @return
     */
    @GetMapping("/idmp")
    public String idmp(){
        return "get请求无需幂等保护";
    }

    /**
     * 未开启
     * @return
     */
    @PostMapping("/idmp2")
    public String idmp2(){
        return "未开启幂等保护 !";
    }

    /**
     * 已开启
     * @return
     */
    @PostMapping("/idmp3")
    @MyIdempotenceEnabled
    public String idmp3(){
        return "幂等保护中";
    }
}
