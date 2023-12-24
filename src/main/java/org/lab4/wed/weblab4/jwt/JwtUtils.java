package org.lab4.wed.weblab4.jwt;

import io.jsonwebtoken.Claims;

public class JwtUtils {

    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setUsername(claims.getSubject());
        return jwtInfoToken;
    }
}
