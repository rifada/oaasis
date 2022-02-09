package com.sinc.goodmd.oassis.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    Member findByUserId(String userId);

    int save(Member member);
}
