package com.example.demo.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

public class AuthUtil {
    // Header 정보를 날릴때 jwt 형식의 데이터를 날려서 식별할 수 있게 해야한다.
    // 스태틱을 쓰면 new를 안해도 쓸 수 가 있었음
    // 이 스트링으로날라오는 헤더에 일곱글자의 jwt토큰이 날려지게 되는 것
    public static long getUserNo(String header) throws Exception {
        String token = header.substring(7);
        // 제약사항을 거는것constants.
        byte[] signingKey = SecurityConstants.JWT_SECRET.getBytes();

        // parsedToken은 아래의 subject까지 포함하여 디코딩된 데이터를 얻는다.
        // jws 웹토큰에 Claims를 넣어주고
        Jws<Claims> parsedToken = Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(token);

        // 파스드된 토큰이 서브젝트가 되고 이걸 파스트인트로 만들어주고 인트가 user no으로 들어감->
        String subject = parsedToken.getBody().getSubject();

        long userNo = Integer.parseInt(subject);

        return userNo;
    }
}
