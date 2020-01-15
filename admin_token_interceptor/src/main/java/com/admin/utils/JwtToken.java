package com.admin.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
@Component
public class JwtToken {
    /** 秘钥 */

    private String secret = "JO6HN3NGIU25G2FIG8V7VD6CK9B6T2Z5";
//    secret: JO6HN3NGIU25G2FIG8V7VD6CK9B6T2Z5
//    expire:
    /** 过期时间(秒) */
//    @Value("${jwt.expire}")
    private long expire = 6000;

    /**
     * 生成token
     * @param userId
     * @return
     */

    public String generateToken(Long userId, String account) {
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(userId + account)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    // 解析token
    public Claims getClaimByToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            System.out.println("validate is token error: " +  e);
            return null;
        }
    }

    /**
     * 验证token 过期
     * @param expiration
     * @return
     */
    public static boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }
}
