package com.busleiman.oauthservice.services;

import com.busleiman.oauthservice.domain.User;

public interface IUserService {

    User findUserByEmail(String email);


    User findUserByUsername(String username);


    void changeStateUser(User user, Long id);
}
