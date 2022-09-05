package com.boy.springbootallredis.service;

import com.boy.springbootallredis.exception.IdempotenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-09-05 15:39
 */
@Service
public class TokenService {
    @Autowired
    RedisService redisService;

    /**
     * 获取一个token，并将其存入redis。 具体生成的token没有限制。 暂时选择用UUID。
     *
     * @return 盛昌的token。
     */
    public String getInstanceAndSetExpire(Long expDurationSecond) {
        String s = UUID.randomUUID().toString();
        boolean b = redisService.setEx(s, s, expDurationSecond);
        return s;
    }

    /**
     * @param request
     * @return 返回token是否存在于redis中， true表明存在，对应第一次请求， false表明不存在， 对应幂等性问题的第2+次请求。
     * @throws IdempotenceException
     */
    public boolean checkToken(HttpServletRequest request) throws IdempotenceException {
        String token = request.getHeader("token");

        if (StringUtils.isEmpty(token)) {
            token = request.getParameter("token");
            if (StringUtils.isEmpty(token)) {
                throw new IdempotenceException("入参数token不存在");
            }
        }
        if (!redisService.exists(token)) {
            throw new IdempotenceException("重复操作, 情形 1: 检查token在redis是否存在前就已经被删除");
        }
        // 逻辑是， 请求幂等性保护的接口时， 都会在拦截器里调用检测token信息， 如果不存在就是上面的逻辑
        // 走到这里说明存在， 那么接口就会在此次请求时删除token，并执行后续的逻辑。 后续再请求时就不会再查到该token。
        // 从而起到保护作用。
        boolean removed = redisService.remove(token);
        if (!removed) {
            throw new IdempotenceException("重复操作, 情形 2: 检查到token在redis存在后,尝试删除该token, 并执行后续业务逻辑时, 发现token被删除");
        }
        return true;
    }
}
