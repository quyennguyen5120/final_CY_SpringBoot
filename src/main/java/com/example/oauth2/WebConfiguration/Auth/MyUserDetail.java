package com.example.oauth2.WebConfiguration.Auth;

import com.example.oauth2.Entity.RoleEntity;
import com.example.oauth2.Entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class MyUserDetail implements UserDetails {

    @Autowired
    UserEntity userEntity;

    public MyUserDetail(UserEntity user){
        this.userEntity = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>(userEntity.getRoles().size());
        for(RoleEntity role : userEntity.getRoles()){
            roles.add(new SimpleGrantedAuthority(role.getName()));
        }
        return roles;
    }

    @Override
    public String getPassword() {
        if(userEntity.getPassword() == null)
            return  null;
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        if(userEntity.getUsername() == null)
            return  null;
        return userEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
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
