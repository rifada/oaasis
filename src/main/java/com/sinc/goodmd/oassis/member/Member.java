package com.sinc.goodmd.oassis.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member implements UserDetails {

    String userId;
    String userPass;
    String userNm;
    String userEmail;
    String userHpno;
    String userRole;
    int otp;
    boolean expired;
    boolean locked;
    boolean enabled;
    String regId;
    LocalDateTime regDate;
    String modId;
    LocalDateTime modDate;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(getUserRole()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return getUserPass();
    }

    @Override
    public String getUsername() {
        return getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return isExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return isLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
