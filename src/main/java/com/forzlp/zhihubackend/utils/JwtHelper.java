package com.forzlp.zhihubackend.utils;

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;
import java.util.Date;

/**
 * @author zlp
 * @date 2023/8/7 22:27
 */
// jwt的工具类
public class JwtHelper {
//    有效时长一天
    private static long tokenExpiration = 24 * 60 * 60 * 1000;
//    签名
    private static String tokenSignKey = "123456";
//    根据用户id和用户名称生成token字符传
    public static String createToken(Long userId, String username) {
        String token = Jwts.builder()
//                分类
                .setSubject("AUTH-USER")
//                设置有效时长
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
//                设置主体部分
                .claim("userId", userId)
                .claim("username", username)
//                签名部分
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }
//        从token中获取用户id
    public static Long getUserId(String token) {
        try {
            if (StringUtils.isEmpty(token)) return null;

            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            Integer userId = (Integer) claims.get("userId");
            return userId.longValue();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
//        获取用户名称
    public static String getUsername(String token) {
        try {
            if (StringUtils.isEmpty(token)) return "";

            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            return (String) claims.get("username");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String token = JwtHelper.createToken(1L, "admin");
        System.out.println(token);
        System.out.println(JwtHelper.getUserId(token));
        System.out.println(JwtHelper.getUsername(token));
    }
}