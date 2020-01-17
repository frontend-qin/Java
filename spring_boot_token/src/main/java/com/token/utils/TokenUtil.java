package com.token.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TokenUtil {
    /**
     * 过期时间15秒
     */
    protected static final long EXPIRE_TIME = 15 * 1000;
    /**
     * token私钥, 每次调用都使用UUID 重新生成一个私钥
     */
    protected static final String PRIVATE_SECRET = UUID.randomUUID().toString();
    // 参考文档： https://github.com/auth0/java-jwt

    /**
     * 签发签名 token
     *
     * @param account 用户账号
     * @param userId  账号id
     * @return String 的 token签名
     */
    public static String signToken(String account, String userId) {
        try {
            // 过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);

            // 参考: https://github.com/auth0/java-jwt#create-and-sign-a-token

            // 私钥及加密 using HS256
            Algorithm algorithm = Algorithm.HMAC256(PRIVATE_SECRET);

            // 头信息 setting
            Map<String, Object> header = new HashMap<String, Object>(2); // 暂定设置容量为 2
            header.put("type", "JWT");
            header.put("alg", "HS256");

            // 生成签名, 带上参数
            String token = JWT.create()
                    .withHeader(header)
                    .withClaim("loginname", account)
                    .withClaim("userId", userId)
                    .withExpiresAt(date)
                    .sign(algorithm);
            // 返回 生成的 token 字符串
            return token;
        } catch (JWTCreationException e) {
            return null;
        }
    }
    // 参考: https://github.com/auth0/java-jwt#verify-a-token

    /**
     * 验证 token
     *
     * @param token 生成的token信息
     * @return 返回 boolean值  (true 有效 | false 失效)
     */
    public static boolean virfityToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(PRIVATE_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

    /**
     * 解析 token
     * @param token 生成的token
     * @return 返回 DecodedJWT对象
     */
    public static DecodedJWT ParsingToken(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt;
        } catch (JWTDecodeException exception) {
            return null;
        }
    }
}
