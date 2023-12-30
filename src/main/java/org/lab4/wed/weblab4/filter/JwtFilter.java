package org.lab4.wed.weblab4.filter;

import java.io.IOException;

import org.lab4.wed.weblab4.jwt.JwtAuthentication;
import org.lab4.wed.weblab4.jwt.JwtProvider;
import org.lab4.wed.weblab4.jwt.JwtUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
// @Component фильтр именованный компонентом Spring будет считаться "глобальным" фильтром 
// и всегда добавлять в цепочку фильтров, что не всегда нужно, как в этом приложении
@NoArgsConstructor
public class JwtFilter extends GenericFilterBean {
    private static final String AUTHORIZATION = "Authorization";

    public JwtProvider jwtProvider(){
        return new JwtProvider();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) 
            throws IOException, ServletException {
                log.debug("Filter start");
        final String token = getTokenFromRequest((HttpServletRequest) request);
        if (token != null && jwtProvider().validateAccessToken(token)) {
            final Claims claims = jwtProvider().getAccessClaims(token);
            final JwtAuthentication jwtInfoToken = JwtUtils.generate(claims);
            jwtInfoToken.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(jwtInfoToken);
        }
        else{
            log.debug("Filter else");
            ((HttpServletResponse)response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return;
        }
        log.debug("Filter end");
        fc.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final String bearer = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }
}
