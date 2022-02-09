package com.sinc.goodmd.oassis.config.jwt;

import com.sinc.goodmd.oassis.config.Constants;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class JwtTokenProvider {

    private static final String SECRET_KEY = "holytigerisbestsoftwaredeveloper";

    private Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    /**
     * 로그인 완료한 userId로 Jwt 토큰을 생성함
     * @param userId
     * @return JwtToken
     */
    public String createJwtToken(String userId, String userRole){
        log.info("JwtTokenProvider::createJwtToken");

        //Header Setting
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ","jwt");
        headers.put("alg","HS256");

        //Payload Setting
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("userRole", userRole);

        Date expired = new Date();
        expired.setTime(expired.getTime() + Constants.ACCESS_TOKEN_EXPIRE_TIME);

        String jwtToken = Jwts.builder()
                .setHeader(headers)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(expired)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        log.debug(jwtToken);

        return jwtToken;
    }

    /**
     * 생성된 Jwt 토큰이 유효한지 체크
     * @param jwtToken
     * @return
     */
    public boolean verifyJwtToken(String jwtToken) {
        log.info("JwtTokenProvider::verifyJwtToken");
        log.debug("JWT Token: "+ jwtToken);

        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtToken.replace(Constants.AUTH_PREFIX,""));
            return true;
        }catch (ExpiredJwtException e){
            log.error("JWT Token is expired: {}", e.getMessage());
        }catch (MalformedJwtException e) {
            log.error("Invalid JWT Token: {}", e.getMessage());
        }catch (IllegalArgumentException e) {
            log.error("Invalid Argument: {}", e.getMessage());
        }

        return false;
    }

    /**
     * 토큰에서 body의 sub(id)값을 추출
     * @param jwtToken
     * @return
     */
    public String extractJwtToken(String jwtToken, String type) {
        log.info("JwtTokenProvider::extractJwtToken");
        log.debug(jwtToken);
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtToken.replace(Constants.AUTH_PREFIX,"")).getBody();

        String returnValue = "";

        if(Constants.USERID.equals(type)){
            returnValue = claims.get("userId").toString();
        }else if(Constants.USERROLE.equals(type)) {
            returnValue = claims.get("userRole").toString();
        }

        log.info("extractToken type: {}, value: {}",type.toString(), returnValue);

        return returnValue;
    }
}
