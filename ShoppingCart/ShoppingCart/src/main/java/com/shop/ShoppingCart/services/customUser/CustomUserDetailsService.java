package com.shop.ShoppingCart.services.customUser;

import com.shop.ShoppingCart.models.User;
import com.shop.ShoppingCart.models.UserInfo;
import com.shop.ShoppingCart.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service("userDetailsService")
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    @Resource
    private UserRepository userRepository;

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User domainUser = userRepository.findByUsername(username);

        if (domainUser == null) {
            throw new UsernameNotFoundException("User Not Found !");
        }

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        return new UserInfo(domainUser, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked);
    }
}
