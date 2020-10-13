package top.alvinsite.demo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import top.alvinsite.demo.exception.ForbiddenException;
import top.alvinsite.demo.model.support.UserInfo;
import xcz.entity.AuthValid;
import xcz.service.AuthValidService;

@Slf4j
@Service
public class AuthValidServiceImpl implements AuthValidService {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public AuthValid isValid(String authorization, String uri) {
        AuthValid authValid = new AuthValid();

        log.info(authorization);
        log.info(uri);

        ValueOperations<String, UserInfo> opsForValue = redisTemplate.opsForValue();
        UserInfo userInfo = opsForValue.get(authorization);

        Assert.notNull(userInfo, "非法Token");

        if ((uri.startsWith("/auth/permission/"))
                && !userInfo.getUserGroup().equals("admin")) {
            throw new ForbiddenException("您所在的用户组不能访问该数据");
        }

        authValid.setValid(userInfo != null);
        authValid.setAccount(userInfo.getAccount());
        authValid.setUserName(userInfo.getNickname());
        return authValid;
    }
}
