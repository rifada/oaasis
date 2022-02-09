package com.sinc.goodmd.oassis.config.jwt;

import com.sinc.goodmd.oassis.config.Constants;
import com.sinc.goodmd.oassis.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Configuration
public class JwtVerificationFilter extends OncePerRequestFilter {

    @Autowired
    private MemberService memberService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    /**
     * OncePerRequestfilter : 한번만 호출되는 필터 (일반 필터로 설정 시, 매 요청마다 필터가 실행됨)
     * doFilterInternal 에서 요청 header 에 jwt토큰이 존재하면 넘겨줌
     * jwt토큰이 존재하지 않는다면, 로그인 처리 후에 jwt토큰 생성 후에 반환
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("JwtVerificationFilter");
        log.debug(request.getHeader(Constants.AUTHORIZATION));

        String authorization = request.getHeader(Constants.AUTHORIZATION);

        try {
            if(authorization!=null && jwtTokenProvider.verifyJwtToken(authorization)){
                String userId = jwtTokenProvider.extractJwtToken(authorization, Constants.USERID);

                UserDetails userDetails = memberService.loadUserByUsername(userId);
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
                log.debug("token="+token);
                SecurityContextHolder.getContext().setAuthentication(token);
            }
        }catch(Exception e){
            log.error(e.getMessage());
        }
        log.info("filterChant before");
        filterChain.doFilter(request,response);
    }
}
