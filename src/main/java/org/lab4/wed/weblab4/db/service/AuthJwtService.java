package org.lab4.wed.weblab4.db.service;

import java.util.HashMap;
import java.util.Map;

import org.lab4.wed.weblab4.db.dto.UserReadDto;
import org.lab4.wed.weblab4.jwt.JwtAuthentication;
import org.lab4.wed.weblab4.jwt.JwtProvider;
import org.lab4.wed.weblab4.jwt.JwtRequest;
import org.lab4.wed.weblab4.jwt.JwtResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthJwtService {
    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final Map<String, String> refreshStorage = new HashMap<>();

    /**
     * Метод ищет пользователя по логину, если пользователь найден и пароль совпадает, 
     * то получаем из JwtProvider токены(refresh token сохраняется в хранилище)
     * @param authRequest
     * @return JwtResponse с токенами
     * @throws AuthException выбрасывается, если пользователь не найден или пароль не совпадает
     */
    public JwtResponse login(@NonNull JwtRequest authRequest) throws AuthException {
        final UserReadDto user = userService.findByName(authRequest.getUserLogin())
                .orElseThrow(() -> new AuthException("Пользователь не найден"));
        if (user.password().equals(authRequest.getUserPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(user);
            final String refreshToken = jwtProvider.generateRefreshToken(user);
            refreshStorage.put(user.name(), refreshToken);
            return new JwtResponse(accessToken, refreshToken);
        } else {
            throw new AuthException("Неправильный пароль");
        }
    }

    /**
     * Метод принимает refreshToken, валидирует его, если валиден, то получает из него claim с логином пользователя.
     * По логину в хранилище(refreshStorage) находим refreshToken, который сверяем с присланным токеном от пользователя, 
     * если токены совпал, то отправляем обхект user в JwtProvider, который генерирует новый accessToken
     * @param refreshToken 
     * @return JwtResponse с новым accessToken
     * @throws AuthException выбрасывается, если пользователь не найден
     */
    public JwtResponse getAccessToken(@NonNull String refreshToken) throws AuthException {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final UserReadDto user = userService.findByName(login)
                        .orElseThrow(() -> new AuthException("Пользователь не найден"));
                final String accessToken = jwtProvider.generateAccessToken(user);
                return new JwtResponse(accessToken, null);
            }
        }
        return new JwtResponse(null, null);
    }

    /**
     * Метод принимает refreshToken, валидирует его, если валиден, то получает из него claim с логином пользователя.
     * По логину в хранилище(refreshStorage) находим refreshToken, который сверяем с присланным токеном от пользователя,
     * если токены совпал, то отправляем обхект user в JwtProvider, который генерирует новый accessToken и refreshToken
     * @param refreshToken
     * @return JwtResponse с новыми accessToken и refreshToken
     * @throws AuthException выбрасывается, если пользователь не найден или refreshToken не валиден
     */
    public JwtResponse refresh(@NonNull String refreshToken) throws AuthException {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final UserReadDto user = userService.findByName(login)
                        .orElseThrow(() -> new AuthException("Пользователь не найден"));
                final String accessToken = jwtProvider.generateAccessToken(user);
                final String newRefreshToken = jwtProvider.generateRefreshToken(user);
                refreshStorage.put(user.name(), newRefreshToken);
                return new JwtResponse(accessToken, newRefreshToken);
            }
        }
        throw new AuthException("Невалидный JWT токен");
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}
