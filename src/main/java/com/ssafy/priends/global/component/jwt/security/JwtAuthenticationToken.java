package com.ssafy.priends.global.component.jwt.security;

import com.ssafy.priends.domain.member.dto.MemberLoginActiveDto;
import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private MemberLoginActiveDto pricipal;
    private Object credentials;

    public JwtAuthenticationToken(MemberLoginActiveDto pricipal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.pricipal = pricipal;
        this.credentials = credentials;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.pricipal;
    }
}
