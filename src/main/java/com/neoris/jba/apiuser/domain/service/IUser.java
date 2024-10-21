package com.neoris.jba.apiuser.domain.service;

import com.neoris.jba.apiuser.domain.entity.User;

import java.util.Optional;

public interface IUser {
    User saveUser(User usuario);

    Optional<User> getUserByEmail(String email);

}
