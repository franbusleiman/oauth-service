package com.busleiman.oauthservice.services;

import com.busleiman.oauthservice.domain.User;

public interface IUserService {

//    User findUserByUsername(String username);

    User findUserByEmail(String email);

    void changeStateUser(User user, Long id);
}
