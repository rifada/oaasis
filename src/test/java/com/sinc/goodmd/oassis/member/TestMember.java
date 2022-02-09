package com.sinc.goodmd.oassis.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

@MybatisTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestMember {

    @Autowired
    private MemberMapper memberMapper;

    @DisplayName("1.신규 멤버 Insert")
    @Test
    void Test01(){
        Member member = Member.builder()
                .userId("holytiger")
                .userPass("1234")
                .userNm("ksh")
                .userEmail("holytiger@kakao.com")
                .userHpno("01026397213")
                .enabled(true)
                .expired(false)
                .locked(false)
                .regId("admin")
                .modId("admin")
                        .build();

        memberMapper.save(member);
    }

    @DisplayName("2.멤버 조회")
    @Test
    void test02(){
        Member member = memberMapper.findByUserId("holytiger");

        Assertions.assertThat(member.getUserId().equals("holytiger"));
        Assertions.assertThat(member.getRegDate().toLocalDate().equals("2022-01-16"));
    }
}
