package top.alvinsite.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Getter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Alvin
 */
public class JwtUtils {

    @Getter
    private static final long EXPIRE_TIME = 15 * 60 * 1000;

    private static final String ENCRYPT_KEY = "privateKey";

    public static String sign(String username) {
        return sign(username, UUID.randomUUID().toString());
    }

    public static String sign(String username, String keyId) {
        // 设置过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        // 私钥和加密算法

        // 设置头部信息
        Map<String, Object> header = new HashMap<>(2);
        header.put("Type", "Jwt");
        header.put("alg", "HS256");

        Algorithm algorithm = Algorithm.HMAC256(ENCRYPT_KEY);
        return JWT.create()
                .withHeader(header)
                .withSubject(username)
                .withExpiresAt(date)
                .withIssuedAt(new Date())
                .withKeyId(keyId)
                .sign(algorithm);
    }

    public static DecodedJWT verify(DecodedJWT jwt) {
        String username = jwt.getSubject();
        Algorithm algorithm = Algorithm.HMAC256(ENCRYPT_KEY);

        JWTVerifier verifier = JWT.require(algorithm)
                .withSubject(username)
                .build();

        return verifier.verify(jwt.getToken());

    }
}
