package com.newsfeed.security;

import com.newsfeed.dao.UserDao;
import com.newsfeed.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by havea on 01/09/15.
 */

@Component
public class NfUserDetailService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user;
        try {
            user = userDao.findByEmail( s );
            if( user == null )
                throw new UsernameNotFoundException("Unknown username");
        }catch (Exception e ){
            throw new UsernameNotFoundException("Unknown error finding user", e);
        }




        List<GrantedAuthority> roles = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
        NfUserDetails details = new NfUserDetails(user.getEmail(), user.getPassword(), roles);
        details.setUser( user );

        return details;
    }
}
