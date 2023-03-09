package com.busleiman.oauthservice.services;

import com.busleiman.oauthservice.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

    @Autowired
    IUserService userService;

    Logger logger = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);

    @Override
    public void publishAuthenticationSuccess(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        logger.info(userDetails.getUsername() + " logged");

        User user = userService.findUserByUsername(authentication.getName());

        if(user.getIntents() != null && user.getIntents() > 0){
            user.setIntents(0);
            userService.changeStateUser(user, user.getId());
        }
    }

    @Override
    public void publishAuthenticationFailure(AuthenticationException e, Authentication authentication) {
        logger.info("Log in error: " + e.getMessage());

        User user = userService.findUserByUsername(authentication.getName());

        if(user.getIntents() ==null){
            user.setIntents(0);
        }

        user.setIntents(user.getIntents() + 1);

        if(user.getIntents() >= 3){
            user.setEnabled(false);
        }

        userService.changeStateUser(user, user.getId());

    }
}
