package org.lab4.wed.weblab4.jwt;

import io.jsonwebtoken.Claims;

public class JwtUtils {

    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setUserName(claims.getSubject());
        jwtInfoToken.setUserId(Long.parseLong(claims.get("id").toString()));
        return jwtInfoToken;
    }
}
