package com.example.demo.security;

public class SecurityConstants {
    // 인증을요청할 때 보내는 url
    public static final String AUTH_LOGIN_URL = "/api/authenticate";
    // http://www.allkeysgenerator.com
    // 토큰을 잡아오는 위치를 잡아주기
    // - howmany를 1로 512-bit allkeygenerater.com
    public static final String JWT_SECRET =
            "dRgUkXp2s5v8y/B?E(G+KbPeShVmYq3t6w9z$C&F)J@McQfTjWnZr4u7x!A%D*G-";

    // JWT 기본 토큰
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "secure-api";
    public static final String TOKEN_AUDIENCE = "secure-app";
}
