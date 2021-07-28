package com.busleiman.oauthservice.services;

import com.busleiman.oauthservice.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AditionalInfoToken implements TokenEnhancer {

    @Autowired
    UserService userService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Map<String, Object> properties = new HashMap<>();
        User user = userService.findUserByUsername(oAuth2Authentication.getName());
        properties.put("name", user.getName());
        properties.put("id", user.getId());
        properties.put("lastName", user.getLastName());
        properties.put("email", user.getEmail());

        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(properties);
        return oAuth2AccessToken;
    }
}
