package com.springcloud.zuul.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    /**
     *
     */
    private static final String SIGNING_KEY = "78sebr72umyz33i9876gc31urjgyfhgj";

    /**
     *
     *
     *
     * @param exp
     * @param claims
     * @return token
     */
    public String createJWT(Date exp, Map<String, Object> claims) {
        //signature algorithm
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;


        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);


        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(signatureAlgorithm, SIGNING_KEY);

        return builder.compact();
    }

    /**
     *
     *
     * @param token
     * @return
     */
    public Claims parseJWT(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(SIGNING_KEY)
                .parseClaimsJws(token).getBody();
        return claims;
    }

}