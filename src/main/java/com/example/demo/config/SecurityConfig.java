package com.example.demo.config;

import com.example.demo.security.CustomAccessDeniedHandler;
import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.security.JwtAuthenticationFilter;
import com.example.demo.security.JwtAuthorizationFilter;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
// Log는 Lombok을 통해 Logger와 LoggerFactory를 자동을 등록하게 지원한다.
@Log
// 웹보안 관련된 방어 기능을 활성화 시킴  jwt의 엑세스 토큰을 사용하겠다
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
// 웹세큐리티 컨피규레이션 어뎁터 프레임웍을 사용하겠다
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // HttpSecurity goto 해서 필드 변수들이 있다는 것을 보기
    // -> implements 2개면 2개 구현해줘야함
    // -> 주석 읽어보면 도움됨!
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("Security Configuration");
        // 이 부분을 자세히 파고 들어가면 너무 빡세지닌 간단하게만 보면
        // 접근 거부, URL 요청이 올바른지, 권한을 가지고 있는지 등등을 설정하는 것이라 보면됨
        // (필요할 때 가져다 쓰기 )
        http.cors()
                // .add()로 위조공격 디나이 하는거 끼리 묶어주고
                // 필터링관리 하는 거 끼리 세션을 다 묶어준 것
                .and()
                .csrf().disable()
                .exceptionHandling()
                // 커스텀 구간 createAccessDeniedHandler
                .accessDeniedHandler(createAccessDeniedHandler())
                .and()
                // 커스텀 구간 JwtAuthenticationFilter
                // 커스텀 구간 JwtAuthorizationFilter
                // 어댑터를 통해 Manager를 호출해서 권한을 관리하는 별도의 객체를 따로 만들었음
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager()))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
    // 비밀번호를 암호화 하기 위한 설정
    // 같은 것 끼리 묶여 있음
    // 위에서 new 2개 한거 커스텀 해서 만들어줘야함 곧
    // 패스워드 인코더를 통해서 패스워드()를 인코드 한다
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(createUserDetailsService())
                .passwordEncoder(createPasswordEncoder());
    }
    // @Bean 스프링 전용객체 만들어주기
    // - 이렇게 하면 createPasswordEncoder 빨간줄 사라짐 bean이 허용하게함
    // - 암호화하는 것을 만들어줌
    @Bean
    public PasswordEncoder createPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService createUserDetailsService() {
        return new CustomUserDetailsService();
    }
    // 코어스 컨피그는 헤더만
    // 얘는 메서드도 허용, Authorization 이 부분도 허용
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        // SecurityConfig에서  Authorization를 Disposition했고
        // -> MemberController에 가면  RequestHeader를 통해 Authorization하겠다고 적어줌
        config.setExposedHeaders(Arrays.asList("Authorization", "Content-Disposition"));

        source.registerCorsConfiguration("/**", config);

        return source;
    }
    // 접근이 거부될 때 동작하게 될 메서드도 지정 = 접근 거부와 관련된 생성자
    // 커스텀 요소 중 하나 나머지는 갖다 쓰면 됨
    @Bean
    public AccessDeniedHandler createAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }
}

//@Bean
//        - 상수값으로 때려밖음
//        - 어디서 들어오든 뭐든 허용하겠습니다 setAllowCredentials
//        - 이미지나 영상은 HEAD로 들어옴
//        - 일반적 요청은 GET, POST DELTE
//        - 수정은 PATCH
//        - 예외처리하는 setExposeHeaders
//        - 전 경로"/**"를 인식하겠습니다 아래서 던진 config를 받아옴
//        - 인증하는 것들은 빼고 나머지는 허용하겠다 ->  보안문제 때문에 하는 것
//
//        ----------
//        코어스컨피규레이션 소스를 코어스가 만들어냄
//        빈으로 만들어내면 쓸 수 있다 이것을 지원하게 하는게 jwt