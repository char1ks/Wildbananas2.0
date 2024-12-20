package com.example.SpringRedisPet_20december.Security;

import com.example.SpringRedisPet_20december.Model.Visitor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class VisitorDetails implements UserDetails {

    private Visitor visitor;

    public VisitorDetails(Visitor visitor) {
        this.visitor = visitor;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(visitor.getRole().name()));
    }

    @Override
    public String getPassword() {
        return visitor.getPassword();
    }

    @Override
    public String getUsername() {
        return visitor.getUsername();
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

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }
}
