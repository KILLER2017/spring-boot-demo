package top.alvinsite.demo.core.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import top.alvinsite.demo.model.support.UserInfo;

@Component
public class StringToUserInfoConverter implements Converter<String, UserInfo> {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public UserInfo convert(String authorization) {
        ValueOperations<String, UserInfo> opsForValue = redisTemplate.opsForValue();
        UserInfo userInfo = (UserInfo) opsForValue.get(authorization);
        return userInfo;
    }
}
