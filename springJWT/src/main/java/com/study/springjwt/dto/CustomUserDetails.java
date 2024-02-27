package com.study.springjwt.dto;

import com.study.springjwt.domain.UserEntity;
import com.study.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {



    private final UserEntity userEntity;

    public  CustomUserDetails(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    // roll값 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return userEntity.getRole();
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }

    // 계정 만료 여부(true: 만료안됨)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠금 여부(true: 안잠김)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 계정 password 만료 여부(true: 만료안됨)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 사용 가능 여부(true: 사용 가능)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
