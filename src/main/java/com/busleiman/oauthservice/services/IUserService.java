package com.busleiman.oauthservice.services;

import com.busleiman.oauthservice.domain.User;

public interface IUserService {

    User findUserByUsername(String username);

    void changeStateUser(User user, Long id);
}
