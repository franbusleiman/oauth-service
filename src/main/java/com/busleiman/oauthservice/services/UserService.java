package com.busleiman.oauthservice.services;

import com.busleiman.oauthservice.clients.UserFeignClient;
import com.busleiman.oauthservice.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService, UserDetailsService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserFeignClient userFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        ResponseEntity<User> userResponseEntity = userFeignClient.findUserByUsername(username);

        if(userResponseEntity.getStatusCode().isError()){

            logger.info(String.format("User %s not found"), username);

            throw new RuntimeException("User not found");
        }

        User user = userResponseEntity.getBody();

        logger.info(String.format("User found"), username);


        List<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), user.isEnabled(), true, true, true, grantedAuthorities);
    }

    @Override
    public User findUserByUsername(String username) {
        ResponseEntity<User> userResponseEntity =  userFeignClient.findUserByUsername(username);

        if(userResponseEntity.getStatusCode().isError()){

            logger.info(String.format("User %s not found"), username);

            throw new RuntimeException("User not found");
        }

        return userResponseEntity.getBody();
    }

    @Override
    public void changeStateUser(User user, Long id) {
        userFeignClient.changeStateUser(user, id);
    }
}
