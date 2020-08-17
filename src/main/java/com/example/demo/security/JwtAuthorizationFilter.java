package com.example.demo.security;

import io.jsonwebtoken.*;

import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
// -롬벅대신 로거 직접써주기  sl4j 써줘야함
// -아파치 isNotempty 지워주기
// object util is empty 써주기
// -그래들에 여러개 넣어줘서 그런거임
// -커머스아이오 지워주기
// if (isNotEmpty 하이버네이트로 써주기)
// import static org.hibernate.internal.util.StringHelper.isNotEmpty;
// import static org.springframework.util.ObjectUtils.isEmpty;
// authenticationManager의 멤버변수가 BasicAuthenticationFilter로 등록
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    // -롬벅대신 로거 직접써주기  sl4j 써줘야함
    private static final Logger log = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    // 헤더가 비어있으면, 프리픽스로 시작하지 않는다면
    // or연산 하나라도 참이면 참 -> 뒤에껀 안보고 아래로 내려가면됨
    // 그러면 필터체인에 두 필터를 하고 있음
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        Authentication authentication = getAuthentication(req);
        String header = req.getHeader(SecurityConstants.TOKEN_HEADER);

        if(isEmpty(header) || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }
    // isEmpty가 null이거나 length값이 0이면 참을 리턴해라 그게아니면 false를 리턴함->
    // CharSequence goto ->implemetaion 해서 보기
    private boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }
    private boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }
    // 요녀석이 얻을 것은 사용자에대한 비번과 토큰
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {
        String token = req.getHeader(SecurityConstants.TOKEN_HEADER);
        // if (isNotEmpty 하이버네이트로 써주기)
        // 토큰잇는상태에서 앞에있는거만 빼서 암호화된걸 보겠다는 뜻
        if (isNotEmpty(token)) {
            try {
                byte[] signingKey = SecurityConstants.JWT_SECRET.getBytes();
                // jws<claims 제이슨 웹토큰 써야함, AuthUtil에서 썼던거 복붙
                Jws<Claims> parsedToken = Jwts.parser()
                        // 사이닝키를 가져왔는데
                        // 토큰을 리플레이스 해서(기존정보를 콘스탄츠에 있는 걸보기 프리픽스가 JWTest넣어주기)
                        .setSigningKey(signingKey)
                        .parseClaimsJws(token.replace("Bearer ", ""));

                String username = parsedToken
                        .getBody()
                        .getSubject();
                // <?>는 와일드카드 데이터타입을 가리지 않고 다 받겠다는 것
                // .map(람다)
                List<SimpleGrantedAuthority> authorities = ((List<?>) parsedToken.getBody()
                        .get("rol")).stream()
                        .map(auth -> new SimpleGrantedAuthority((String) auth))
                        .collect(Collectors.toList());
                // if(isNotEmpty 임포트(비어있지 않다면 username)
                if(isNotEmpty(username)) {
                    return new UsernamePasswordAuthenticationToken(
                            username, null, authorities
                    );
                }
            } catch (ExpiredJwtException e) {
                log.warn("Request: parse expired JWT - {} failed: {}",
                        token, e.getMessage());
            } catch (UnsupportedJwtException e) {
                log.warn("Request: parse unsupported JWT - {} failed: {}",
                        token, e.getMessage());
            } catch (MalformedJwtException e) {
                log.warn("Request: parse invalid JWT - {} failed: {}",
                        token, e.getMessage());
            } catch (SignatureException e) {
                log.warn("Request: parse JWT with invalid signature - {} failed: {}",
                        token, e.getMessage());
            } catch (IllegalArgumentException e) {
                log.warn("Request: parse empty or NULL - {} failed: {}",
                        token, e.getMessage());
            }
        }

        return null;
    }
}

// ExpiredJwtException -> 만료되면 캐치하기
//UnsupportedJwtException ->지원되지 않는 익셉션도 뿌려주기
//malform -> 공격들어오면 invaled하고 캐치해서 알려주기
//시그니처 안맞는경우 srf위조할 수 있으니까
//잘못된 아규먼트 넘어왔을 때 empty or null 로 보여줘라
//
//}잘되면 유저 토큰패스워드를 리턴하고 잘못되면 null값을 리턴하라는 뜻