package com.devaj.apijwtaws.springapijwtaws.security;

import com.devaj.apijwtaws.springapijwtaws.constant.SecurityConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

@Component
public class JwtManager {

    public String createToken(String email, List<String> roles){

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, SecurityConstant.JWT_EXP_DAYS);

        String jwt = Jwts.builder()
                .setSubject(email)
                .setExpiration(calendar.getTime())
                .claim(SecurityConstant.JWT_ROLE_KEY, roles)
                .signWith(SignatureAlgorithm.HS512, SecurityConstant.API_KEY.getBytes())
                .compact();

        return jwt;
    }

    public Claims parseToken(String jwt) throws JwtException {
        Claims claims = Jwts.parser()
                            .setSigningKey(SecurityConstant.API_KEY.getBytes())
                            .parseClaimsJws(jwt)
                            .getBody();
        return claims;
    }
}
