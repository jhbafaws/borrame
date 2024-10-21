package com.neoris.jba.apiuser.domain.service.impl;

import com.neoris.jba.apiuser.domain.entity.User;
import com.neoris.jba.apiuser.domain.repository.UserRepository;
import com.neoris.jba.apiuser.domain.service.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserImpl implements IUser {
    @Autowired
    UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
