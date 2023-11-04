package com.example.skeleton.global.config.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    public final static String HEADER_AUTHOR = "Authorization"; // JWT는 HTTP 요청의 Authorization 헤더에 포함
    public final static String TOKEN_PREFIX = "Bearer "; //JWT 토큰은 "Bearer"라는 프리픽스와 함께 전송

    /**
     * HTTP 요청을 필터링하고 토큰을 검증하여 인증 정보를 설정
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 토큰이 필요하지 않은 API URI의 배열 구성
        List<String> uriListWithoutToken = Arrays.asList(
                "/swagger-ui/**", "/h2-console/**", "/api/clients/sign-up", "/api/clients/sign-in"
        );

        // 토큰이 필요하지 않은 URI들은 JWT 관련 로직을 거치지 않고 다음 필터로 이동
        if (uriListWithoutToken.contains(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        // 요청 헤터의 AUTHORIZATION 키 값 조회
        String authorizationHeader = request.getHeader(HEADER_AUTHOR);

        if (authorizationHeader != null && !authorizationHeader.equalsIgnoreCase("")) {
            // 가져온 값에서 TOKEN_PREFIX "Bearer "를 제거한 실제 토큰값
            String token = getAccessToken(authorizationHeader);

            if (jwtTokenProvider.validToken(token)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Request Header의 인증 토큰을 확인하여 TOKEN_PREFIX 제거하고 토큰 반환
     * @param authorizationHeader
     */
    private String getAccessToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
            return authorizationHeader.substring(TOKEN_PREFIX.length());
        }

        return null;
    }
}
