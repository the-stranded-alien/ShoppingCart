package com.shop.ShoppingCart.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

public class UserInfo extends org.springframework.security.core.userdetails.User {

    private final User user;
    private final Long id;

    private Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();

    public UserInfo(User user, Boolean enabled, Boolean accountNonExpired, Boolean credentialsNonExpired, Boolean accountNonLocked) {

        super(user.getUsername(),
                user.getPassword(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                getGrantedAuthorities(user.getUserRoles()));

        this.user = user;
        this.id = user.getId();

        for(Role role : user.getUserRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
    }

    public static List<GrantedAuthority> getGrantedAuthorities(Set<Role> userRoles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : userRoles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return id;
    }

    public Collection<GrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
    }

    public void setGrantedAuthorities(Collection<GrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }
}
