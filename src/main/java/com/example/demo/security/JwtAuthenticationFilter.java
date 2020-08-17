package com.example.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        // 인자로 들어온 URL로 가겠다.
        // 로그인폼 에밋을 통해 로그인페이지의 온서브밋을 호출 이게 액션의 로그인을 불러냄(axios 로그인 authUrl)
        setFilterProcessesUrl(SecurityConstants.AUTH_LOGIN_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res)
            throws AuthenticationException {
        // getParameter username 찾아서 뭘로 아이디가 들어왔는지 찾게하는 것
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // 여기부터 토큰 확인하는 곳
        Authentication authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        // super필요없음 위에 있으니까
        // authenticate 로 어센티케이트토큰을 보낸다.
        // authenticationManager를 호출한다.
        return authenticationManager.authenticate(authenticationToken);
    }
    // -인증하면 사용자에대한 정보필요함
    // 이 사용자가 어떤 권한가지고 있는지 알려주기위해 응답해야함
    // 뷰쪽에서 로그인하면 뷰객체가 엑세스 토큰을 가지고있게 만들어야하고
    // 가지고 있다면 로그인이 되어있는 상태 없다면
    // 로그인이 안되어잇는상태라는걸 인식하게 만들어줘야함
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication authResult)
            throws IOException, ServletException {
        // CustomUser에서 get을 통해 정보를 얻어오고 이정보를 리스트 형태로 만들어주는 것
        CustomUser user = ((CustomUser) authResult.getPrincipal());

        List<String> roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        // - SERCRET에서getBytes 추가 얻어온다.
        byte[] signingKey = SecurityConstants.JWT_SECRET.getBytes();

        String token = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
                .setIssuer(SecurityConstants.TOKEN_ISSUER)
                .setAudience(SecurityConstants.TOKEN_AUDIENCE)
                .setSubject("" + user.getMember().getUserNo())
                .setExpiration(new Date(System.currentTimeMillis() + 360000000))
                .claim("rol", roles)
                .compact();

        res.addHeader(SecurityConstants.TOKEN_HEADER, SecurityConstants.TOKEN_PREFIX + token);
    }
}





//        res.addHeader(SecurityConstants.TOKEN_HEADER,
//                SecurityConstants.TOKEN_PREFIX + token);
//
//        super.successfulAuthentication(req, res, chain, authResult);
//    }
//}
