package com.sinc.goodmd.oassis.authentication;

import com.sinc.goodmd.oassis.config.jwt.JwtTokenProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class TestJwt {

    JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();

    String token;

    @DisplayName("1.JWT Token 생성")
    @BeforeEach
    @Test
    void test01(){
        token = jwtTokenProvider.createJwtToken("holytiger", "ADMIN");
        //System.out.println(token);
    }

    @DisplayName("2. JWT Token 검증")
    @Test
    void test02() {
        Assertions.assertTrue(jwtTokenProvider.verifyJwtToken(token));
    }

    @DisplayName("3. JWT Token 추출")
    @Test
    void test03() {
        Assertions.assertEquals(jwtTokenProvider.extractJwtToken(token,"USERID"), "holytiger");
        Assertions.assertEquals(jwtTokenProvider.extractJwtToken(token,"USERROLE"), "ADMIN");
    }
}
