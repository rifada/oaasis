package com.sinc.goodmd.oassis.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MemberService implements UserDetailsService {

    @Autowired
    private MemberMapper memberMapper;

    //@Autowired
    //private PasswordEncoder passwordEncoder;

    public int save(Member member){
        //member.setUserPass(passwordEncoder.encode(member.getPassword()));
        return memberMapper.save(member);
    }

    public Member findByUserId(String userId){
        return memberMapper.findByUserId(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
        return memberMapper.findByUserId(userid);
    }
}
