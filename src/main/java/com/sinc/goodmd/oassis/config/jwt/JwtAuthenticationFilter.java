package com.sinc.goodmd.oassis.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinc.goodmd.oassis.config.Constants;
import com.sinc.goodmd.oassis.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * /login을 담당하는 Jwt login Fillter다
 * 일반 컨트롤러에서 /login을 구현하는 것은 권장하지 않음.
 * WebSecurityConfig에서 formLogin을 disable시켜놓았기 때문에 기본 /login은 무시된다.
 */

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * spring security의 /login 을 이용하여 로그인 호출 시 실행되는 메소드
     * @param request
     * @param response
     * @return
     * @throws
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("JwtAuthenticationFilter::attemptAuthentication");

        ObjectMapper om = new ObjectMapper();
        Member member = new Member();

        try {
            member = om.readValue(request.getInputStream(), Member.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.debug(member.getUserId()+"::"+member.getUserPass());
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(member.getUserId(), member.getUserPass());
        log.debug(token.toString());
        Authentication authentication = authenticationManager.authenticate(token);
        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("JwtAuthenticationFilter::successfulAuthentication");

        UserDetails userDetails = (UserDetails) authResult.getPrincipal();
        log.debug(userDetails.toString());
        String token = jwtTokenProvider.createJwtToken(userDetails.getUsername(), userDetails.getAuthorities().toString());
        log.debug(token);
        response.setHeader(Constants.AUTHORIZATION, Constants.AUTH_PREFIX+token);

        PrintWriter writer = response.getWriter();
        writer.write(token);
        //super.successfulAuthentication(request, response, chain, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.info("JwtAuthenticationFilter::unsuccessfulAuthentication");
        log.info(failed.getMessage());
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
